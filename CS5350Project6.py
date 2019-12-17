from builtins import range

import numpy as np
import csv
from scipy.sparse import csr_matrix
from libsvm import read_libsvm
import numpy as np
import json
from data import Data
import numpy as np
import math
DATA_DIR = 'data/'
import random
import string

def printEpoch(i,X_train,y_train,w,b):
    CurrentAccuracy = accuracy(X_train, y_train, w, b)
    item = "("+ str(i + 1) + "," + str(CurrentAccuracy) +")"
    print(item)
    return CurrentAccuracy

def generate_data(num_samples):
    size = num_samples // 2
    x1 = np.random.multivariate_normal([0, 0], np.eye(2), size)
    y1 = -np.ones(size).astype(int)
    x2 = np.random.multivariate_normal([3, 3], np.eye(2), size)
    y2 = np.ones(size).astype(int)

    X = np.vstack((x1, x2))
    y = np.append(y1, y2)

    return X, y


def SimplePerceptronPredictUpdate(x, y, w, b, lr):

    x = x.flat
    for i in range(len(w)):
        w[i] = w[i] + x[i] * lr * y

    b = b + lr * y

    return w, b

def predict(X, w, b):
    activation = np.dot(X,w) + b
    if activation >= 0.0:
        return 1.0
    else:
        return -1.0

def predict1(X, w, b):
    activation = np.dot(X,w) + b
    if activation >= 0.0:
        return 1.0
    else:
        return 0

def accuracySVM(X, y, w, b):
    # FILL IN
    # pass
    size = len(X)
    correct = 0
    for i in range(size):
        pred = predict(X[i], w, b)
        if pred == y[i]:
            correct = correct + 1

    return correct / size

# NEEDED
def shuffle_arrays(X, y):
    idx = np.arange(X.shape[0])
    np.random.shuffle(idx)
    return X[idx], y[idx]

def WToWB(w):
    size = len(w)
    newW = []
    for index in range(size-1):
        newW.append(w[index])
    b = w[size-1]
    return newW, b

# SVM
def SimplePerceptron(X_train, y_train, epochs, lr,c):
    w = np.random.uniform(-0.01, 0.01, size=X_train.shape[1])  # initialize w
    b = 0 # np.random.uniform(-0.01, 0.01, 1)  # initialize bias
    bestE = 0
    bestER = 0

    for epoch in range(epochs):
        x_shuffle, y_shuffle = shuffle_arrays(X_train, y_train)
        for x, y in zip(x_shuffle, y_shuffle):
            # pred = predict(x, w, b)
            # if pred != y:
            #     w,b = SimplePerceptronPredictUpdate(x,y,w,b,lr)
            if (np.dot(x,w) + b)*y <= 1:
                x = x.flat
                for i in range(len(w)):
                    w[i] = (w[i]* (1-(lr/(1+epoch)))) + (x[i] * (lr/(1+epoch)) * y * c)

                b = b * (1-(lr/(1+epoch))) + ((lr/(1+epoch)) * y * c)
            else:
                x = x.flat
                for i in range(len(w)):
                    w[i] = w[i] * (1-(lr/(1+epoch)))

                b = b * (1-(lr/(1+epoch)))


        if printEpochs:
            ac = printEpoch(epoch,X_train,y_train,w,b)
            if bestER < ac:
                bestER = ac
                bestE = epoch
    if printEpochs:
        return bestE + 1, bestER
    else:
        return w, b

'''
This method calculates the entropy of the result column
'''
def entropyResult(entropyResultData_obj):
    column = entropyResultData_obj.get_column('label')
    total = len(column)
    pSum = 0
    eSum = 0

    for row in column:
        if row == 'p':
            pSum = pSum + 1
        else:
            eSum = eSum + 1

    if pSum == 0 or eSum == 0:
        return 0

    pFrac = pSum / total
    eFrac = eSum / total
    return (-eFrac*math.log(eFrac,2)-pFrac*math.log(pFrac,2))

'''
This method returns entropy of a column value 
'''
def entropy(entropyData_obj):
    total = len(entropyData_obj)
    pSum = 0
    eSum = 0

    for row in entropyData_obj:
        if row[1] == 'p':
            pSum = pSum + 1
        else:
            eSum = eSum + 1

    if pSum == 0 or eSum == 0:
        return 0

    pFrac = pSum / total
    eFrac = eSum / total
    return (-eFrac * math.log(eFrac, 2) - pFrac * math.log(pFrac, 2))

'''
This method calculates the expected Entropy
'''
def expectedEntropy(expectedEntropyData_obj, expectedEntropyValues, expectedEntropyAttribute):
    total = len(expectedEntropyData_obj)
    expectedEntropyValue = 0

    for value in expectedEntropyValues:
        entropyValue = entropy(expectedEntropyData_obj.get_row_subset(expectedEntropyAttribute, value).get_column([expectedEntropyAttribute, 'label']))
        size = len(expectedEntropyData_obj.get_row_subset(expectedEntropyAttribute, value).get_column([expectedEntropyAttribute, 'label']))
        expectedEntropyValue = expectedEntropyValue + ((size / total) * entropyValue)

    return expectedEntropyValue

'''
This calculates information gain
'''
def informationGain(infoGainData_obj, infoGainAttributes):
    resultEntropy = entropyResult(infoGainData_obj)

    informationGainResult = {}

    for attribute in infoGainAttributes:
        expectedEntropyResult = expectedEntropy(infoGainData_obj, infoGainData_obj.get_attribute_possible_vals(attribute), attribute)
        informationGainResult[attribute] = resultEntropy - expectedEntropyResult

    return informationGainResult

'''
This method checks if all the labels are the same
'''
def sameLabelChecker(sameLabelCheckerData_obj):
    column = sameLabelCheckerData_obj.get_column('label')
    label = None
    checker = False

    for row in column:
        if checker == False:
            checker = True
            label = row
        else:
            if label != row:
                return False

    return checker

'''
This returns the next attribute based off information gained
'''
def nextRoot(nextRootAttributes, nextRootData_obj):
    informationGainResult = informationGain(nextRootData_obj, nextRootAttributes)
    maxValue = -1
    attributeRoot = "Carlos"

    for attribute in nextRootAttributes:
        if informationGainResult[attribute] > maxValue:
            maxValue = informationGainResult[attribute]
            attributeRoot = attribute

    return attributeRoot

'''
Finds the majority label
'''
def majority(majorityData_obj):
    column = majorityData_obj.get_column('label')
    total = len(column)
    pSum = 0
    eSum = 0

    for row in column:
        if row == 'p':
            pSum = pSum + 1
        else:
            eSum = eSum + 1

    if pSum > eSum:
        return 'p'
    else:
        return 'e'

'''
This returns the expected Label given a row and using a decision tree
'''
def expectedLabel(expectedLabelAttributes, expectedLabelRow, expetedLabelRoot):
    if expetedLabelRoot.value is not None:
        # print(root.type)
        return expetedLabelRoot.value
    else:
        index = expectedLabelAttributes[expetedLabelRoot.attribute].index
        for kid in expetedLabelRoot.kids:
            branch = expectedLabelRow[index + 1]
            if branch == kid.branch:
                return expectedLabel(expectedLabelAttributes, expectedLabelRow, kid)

'''
This calculates the accuracy of a program
'''
def accuracy(real, predicted):
    size = len(real)
    size1 = len(predicted)
    correct = 0

    for i in range(size):

        majorityC = 0
        for j in range(size1):
            if predicted[j][i] == 'e':
                majorityC = majorityC + 1
        if majorityC > size1 / 2:
            result = 'e'
        else:
            result = 'p'
        if result == 'e' and real[i] == 1:
            correct = correct + 1
        if result == 'p' and real[i] == -1:
            correct = correct + 1
    return correct / size

'''
This class represents a Node that helps create a tree
attribute = the branch of the tree
type = root or leaf
kids = the children of the root
value = the label of the leaf
'''
class Node():
    def __init__(self, type):
        self.type = type
        self.attribute = None
        self.branch = None
        self.kids = []
        self.value = None

'''
This method creates a decision tree and returns the root node to the tree.
 The method uses the ID3 algorithm, uses the majority when no answer.
'''
def id3(id3Attributes, id3Data_obj):
    id3AttributesCopy = id3Attributes.copy()
    if sameLabelChecker(id3Data_obj):
        leaf = Node("leaf")
        leaf.value = next(iter(id3Data_obj.get_column('label')))
        return leaf
    else:
        root = Node("root")
        attribute = nextRoot(id3AttributesCopy, id3Data_obj)
        root.attribute = attribute
        branches = id3Data_obj.attributes[attribute].possible_vals
        id3AttributesCopy.pop(attribute)
        for branch in branches:
            data_subset = id3Data_obj.get_row_subset(attribute, branch)
            if len(data_subset) == 0:
                label = majority(id3Data_obj)
                leaf = Node("leaf")
                leaf.value = label
                leaf.branch = branch
                root.kids.append(leaf)
            else:
                kid = id3(id3AttributesCopy,data_subset)
                kid.branch = branch
                root.kids.append(kid)

        return root

'''
This method creates a decision tree and returns the root node to the tree.
 The method uses the ID3 algorithm, uses the majority when no answer.
 This has depth control
'''
def id3Depth(id3Attributes, id3Data_obj,depth):
    id3AttributesCopy = id3Attributes.copy()
    if sameLabelChecker(id3Data_obj):
        leaf = Node("leaf")
        leaf.value = next(iter(id3Data_obj.get_column('label')))
        return leaf
    elif depth == 0:
        leaf = Node("leaf")
        label = majority(id3Data_obj)
        leaf.value = label
        return leaf
    else:
        root = Node("root")
        attribute = nextRoot(id3AttributesCopy, id3Data_obj)
        root.attribute = attribute
        branches = id3Data_obj.attributes[attribute].possible_vals
        id3AttributesCopy.pop(attribute)
        for branch in branches:
            data_subset = id3Data_obj.get_row_subset(attribute, branch)
            if len(data_subset) == 0:
                label = majority(id3Data_obj)
                leaf = Node("leaf")
                leaf.value = label
                leaf.branch = branch
                root.kids.append(leaf)
            else:
                kid = id3Depth(id3AttributesCopy,data_subset, depth - 1)
                kid.branch = branch
                root.kids.append(kid)

        return root

'''
This method calculates the depth of the tree
'''
def depth(root,level):
    if root.value is not None:
        return level
    else:
        max = level
        for x in root.kids:
            dep = depth(x,level+1)
            if dep > max:
                max = dep
        return max

def prediction(accuracyData_obj, accuracyRoot):
    rows = accuracyData_obj.raw_data
    _predictions =[]

    for row in rows:
        expected = expectedLabel(accuracyData_obj.attributes, row, accuracyRoot)
        _predictions.append(expected)

    return _predictions

def standardDeviation(average, accu):
    sum = 0
    sum = sum + ((accu[0] - average) * (accu[0] - average))
    sum = sum + ((accu[1] - average) * (accu[1] - average))
    sum = sum + ((accu[2] - average) * (accu[2] - average))
    sum = sum + ((accu[3] - average) * (accu[3] - average))
    sum = sum + ((accu[4] - average) * (accu[4] - average))
    sum = sum / 5
    return math.sqrt(sum)

def labelDivider(item):
    if item < 300:
        return 'a'
    elif item >= 300 and item < 350:
        return 'f'
    elif item >= 350 and item < 400:
        return 'g'
    elif item >= 400 and item < 450:
        return 'b'
    elif item >= 450 and item < 500:
        return 'c'
    elif item >= 500 and item < 550:
        return 'd'
    else:
        return 'e'

def int_to_Char_Array(_attributes, _labels, index,size):
    _matrix = []
    for x in range(100):
        z = random.randint(0, size)
        _new_row = []
        for y in range(50):
            currentIndex = index[y]
            if y == 0:
                if _labels[z] == 0:
                    _new_row.append('p')
                else:
                    _new_row.append('e')
                _new_row.append(labelDivider(_attributes[z].flat[currentIndex]))
            else:
                _new_row.append(labelDivider(_attributes[z].flat[currentIndex]))
        _matrix.append(_new_row)
    return _matrix

def int_to_Char_Array1(_attributes, _labels, index):
    _matrix = []

    # _new_labels = []
    # _new_labels.append("label")
    # for h in range(361):
    #     _new_labels.append(randomString())
    # _matrix.append(_new_labels)

    _label_counter = 0
    # each row
    for _rowItem in _attributes:
        _row = _rowItem.flat
        _size = len(_row)
        _new_row = []
        for i in range(50):
            currentIndex = index[i]
            if i == 0:

                if _labels[_label_counter] == 0:
                    _new_row.append('p')
                else:
                    _new_row.append('e')
                _label_counter = _label_counter + 1

                # _new_row.append(str(_labels[i]))

                _new_row.append(labelDivider(_row[currentIndex]))
                #_new_row.append(str(_row[i]))
            else:
                _new_row.append(labelDivider(_row[currentIndex]))
                #_new_row.append(str(_row[i]))
        _matrix.append(_new_row)
    return _matrix

def formatFile(x,y, index,size):
    f = open("fileFormated","w+")
    f.write("label,qtzozgngjj,aaipjwlktc,apjvufqxxn,avcczrqnnq,ivnsksucxt,jypjefzaaa,ortcpazbwk,gaoeqocezd,hwidfxszaa,jsngjfxkzk,bvvxifgowr,qramhliqbg,akqauimtyp,welifkeezu,dqvlfcdffl,gnxcerdnsd,jwzvllcdlg,bakwnehbac,eiocbqnjvr,encngoyull,dlykutqdrq,vjvfmcobee,ejxjubixyd,doiqkwctmz,mbuxnbmusj,krevljhjpn,mlxskabugx,kwsamulvdo,wabdosyexb,saircucfzr,vsbddlyrfw,nmaarfqiza,xavojfmtbb,onkqnjnijh,jcndwgixyy,hdvdvjhwwo,vkclavkfkx,vptnvgprpe,dctpgnakag,myiabqjfuy,lqrwpkzjhi,hucapunqqj,tjygkewzvf,eadcffglnj,hamjhisnde,zkfzzhxuer,zswmdfxfrt,ugbwajypmy,coblfvzxbc,aewoqplqmj\n")
    if full:
        m = int_to_Char_Array1(x, y, index)
    else:
        m = int_to_Char_Array(x, y,index,size)
    for i in range(len(m)):
        row = m[i]
        list = ''
        for j in range(len(row)):
            if j == 0:
                list = list + row[j]
            else:
                list = list + ',' + row[j]
        f.write(list + "\n")
    f.close()

def predictionToSVM(y, pre):
    f = open("SVMFile", "w+")
    list = ""
    for i in range(len(y)):

        if y[i] == 0:
            list = list + str(-1)
        else:
            list = list + str(y[i])
        for j in range(len(pre)):
            value = 0
            if pre[j][i] == 'p':
                value = 0
            else:
                value = 1
            list = list + " "  + str(j) + ":" + str(value)
        f.write(list + "\n")
        list = ""

######################################################################################
bestLearningRate = 0
bestAccuracy = 0
learningRates = [10, 1, 0.1, 0.01, 0.001, 0.0001]
combineFoldsNames = ["fold1234", "fold1235", "fold1245", "fold1345", "fold2345"]
singleFoldNames = ["fold5", "fold4", "fold3", "fold2", "fold1"]
SimplePerceptronHyperParameter = 0
SimplePerceptronEpoch = 20
printEpochs = False
bestC = 10
######################################################################################

# 255 column
# 1000 rows

random.seed(2)
full = False
bestSize = 20
bestaccuracy = 0
_bestIndex = []
SizeOfTheForest = [10, 50, 100]
combineFoldsNames = ["fold1234", "fold1235", "fold1245", "fold1345", "fold2345"]
singleFoldNames = ["fold5", "fold4", "fold3", "fold2", "fold1"]

for CINDEX in range(6):
    for LRINDEX in range(6):
        for i in range(3):
            size = SizeOfTheForest[i]
            accuracies = []
            for j in range(5):
                combineFoldName = combineFoldsNames[j]
                singleFoldName = singleFoldNames[j]
                _indexCollection = []
                _root = []
                X_train, y_train, num_features = read_libsvm(combineFoldName)
                x = X_train.todense()
                for y in range(size):
                    _index = []
                    for z in range(50):
                        _index.append(random.randint(0, 359))
                    formatFile(x, y_train,_index, 6075)
                    trainData = np.loadtxt('fileFormated', delimiter=',', dtype=str)
                    trainData_obj = Data(data=trainData)
                    attributesSet = trainData_obj.attributes
                    root = id3Depth(attributesSet, trainData_obj, 1)
                    _root.append(root)
                    _indexCollection.append(_index)

                _predictions =[]
                full = True
                X_train, y_train, num_features = read_libsvm(singleFoldName)
                x = X_train.todense()
                for z in range(size):
                    formatFile(x, y_train, _indexCollection[z], 867598)
                    trainData = np.loadtxt('fileFormated', delimiter=',', dtype=str)
                    trainData_obj = Data(data=trainData)
                    _predictions.append(prediction(trainData_obj, _root[z]))
                full = False
                predictionToSVM(y_train, _predictions)
                X_train, y_train, num_features = read_libsvm("SVMFile")

                w, b = SimplePerceptron(X_train.todense(), y_train, 10, learningRates[LRINDEX], learningRates[CINDEX])
                thisAccuracy = accuracySVM(X_train.todense(), y_train, w, b)
                print("Accuracy", size, learningRates[LRINDEX], learningRates[CINDEX], thisAccuracy)
                accuracies.append(thisAccuracy)

            sum = 0
            for j in range(5):
                sum += accuracies[j]
            average = sum / 5

            if average > bestaccuracy:
                bestSize = size
                bestC = learningRates[CINDEX]
                bestLearningRate = learningRates[LRINDEX]
                bestaccuracy = average

            print("Average",size,learningRates[LRINDEX],learningRates[CINDEX],average)

print("BestAverage:",bestaccuracy,bestLearningRate,bestC,bestSize)

full = False
_indexCollection = []
_root = []
X_train, y_train, num_features = read_libsvm('data_train')
x = X_train.todense()
for y in range(bestSize):
    _index = []
    for z in range(50):
        _index.append(random.randint(0, 359))
    formatFile(x, y_train,_index, 7595)
    trainData = np.loadtxt('fileFormated', delimiter=',', dtype=str)
    trainData_obj = Data(data=trainData)
    attributesSet = trainData_obj.attributes
    root = id3Depth(attributesSet, trainData_obj, 2)
    _root.append(root)
    _indexCollection.append(_index)

_predictions =[]
full = True
for z in range(bestSize):
    formatFile(x, y_train, _indexCollection[z], 86759809)
    trainData = np.loadtxt('fileFormated', delimiter=',', dtype=str)
    trainData_obj = Data(data=trainData)
    _predictions.append(prediction(trainData_obj, _root[z]))

predictionToSVM(y_train,_predictions)
X_train, y_train, num_features = read_libsvm("SVMFile")
# w,b = SimplePerceptron(X_train.todense(), y_train, SimplePerceptronEpoch, bestLearningRate, bestC)
# w,b = SimplePerceptron(X_train.todense(), y_train, 20, .1, 10)
# w,b = SimplePerceptron(X_train.todense(), y_train, 20, .01, 10)
w,b = SimplePerceptron(X_train.todense(), y_train, 20, .01, 10)
print("Train Accuracy:",accuracySVM(X_train.todense(),y_train,w,b))

_predictions =[]
full = True
X_train, y_train, num_features = read_libsvm('data_test')
x = X_train.todense()
for z in range(bestSize):
    formatFile(x, y_train, _indexCollection[z], 86759809)
    trainData = np.loadtxt('fileFormated', delimiter=',', dtype=str)
    trainData_obj = Data(data=trainData)
    _predictions.append(prediction(trainData_obj, _root[z]))

predictionToSVM(y_train,_predictions)
X_train, y_train, num_features = read_libsvm("SVMFile")
print("Test Accuracy:",accuracySVM(X_train.todense(),y_train,w,b))

_predictions =[]
full = True
X_train, y_train, num_features = read_libsvm('data_real')
x = X_train.todense()
for z in range(bestSize):
    formatFile(x, y_train, _indexCollection[z], 86759809)
    trainData = np.loadtxt('fileFormated', delimiter=',', dtype=str)
    trainData_obj = Data(data=trainData)
    _predictions.append(prediction(trainData_obj, _root[z]))

predictionToSVM(y_train,_predictions)

X_test, y_test, num_features = read_libsvm("SVMFile")
print()
with open('IDs') as csvfile:
    readCSV = csv.reader(csvfile, delimiter=',')
    with open('result.csv', mode='w') as employee_file:
        result_Writer = csv.writer(employee_file, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
        result_Writer.writerow(['example_id', 'label'])
        counter = 0
        X = X_test.todense()
        for row in readCSV:
            result_Writer.writerow([row[0],int(predict1(X[counter],w,b))])
            counter = counter + 1