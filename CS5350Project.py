import numpy as np
import csv
from scipy.sparse import csr_matrix
from libsvm import read_libsvm
import numpy as np
import json

check1 = False

def printEpoch(i,X_train,y_train,w,b):
    CurrentAccuracy = accuracy(X_train, y_train, w, b)
    item = "("+ str(i + 1) + "," + str(CurrentAccuracy) +")"
    print(item)
    return CurrentAccuracy

def DecayingTheLearningRateUpdate(x, y, w, b, lr, t):

    if y == 0:
        y = -1

    currentlr = lr / (1 +t)
    x = x.flat
    for i in range(len(w)):
        w[i] = w[i] + x[i] * currentlr * y

    b = b + currentlr * y

    return w, b

def predict(X, w, b):
    activation = np.dot(X,w) + b
    if activation >= 0.0:
        return 1 # 1.0
    else:
        return 0 # -1.0

def accuracy(X, y, w, b):
    size = len(X)
    correct = 0
    for i in range(size):
        pred = predict(X[i], w, b)
        if pred == y[i]:
            correct = correct + 1
    return correct / size


def shuffle_arrays(X, y):
    idx = np.arange(X.shape[0])
    np.random.shuffle(idx)
    return X[idx], y[idx]

def DecayingTheLearningRate(X_train, y_train, epochs, lr,learningRate):
    np.random.seed(20)
    w = np.random.uniform(-0.01, 0.01, size=X_train.shape[1])  # initialize w
    b = 0  # initialize bias
    t = 0
    bestE = 0
    bestER = 0
    count = 0

    for epoch in range(epochs):
        x_shuffle, y_shuffle = shuffle_arrays(X_train, y_train)
        for x, y in zip(x_shuffle, y_shuffle):
            pred = predict(x, w, b)
            if pred != y:
                count = count + 1
                w,b = DecayingTheLearningRateUpdate(x, y, w, b, lr, t)

        if printEpochs:
            ac = printEpoch(epoch,X_train,y_train,w,b)
            if bestER < ac:
                bestER = ac
                bestE = epoch

        t = t + 1

    if printEpochs:
        return bestE + 1, bestER
    else:
        if check1:
            print("Number Of Updates:",count)
        return w, b

##########################################################################################################

bestLearningRate = 0
bestAccuracy = 0
learningRates = [1,0.1, 0.01]
combineFoldsNames = ["fold1234", "fold1235", "fold1245", "fold1345", "fold2345"]
singleFoldNames = ["fold5", "fold4", "fold3", "fold2", "fold1"]
DecayingTheLearningRateHyperParameter = 0
DecayingTheLearningRateEpoch = 0
printEpochs = False


X_train, y_train, num_features = read_libsvm('data_train')
X_test, y_test, _ = read_libsvm('data_test', num_features)

##########################################################################################################

print("Cross Validation")
print()

##########################################################################################################

bestLearningRate = 0
bestAccuracy = 0

for i in range(3):
    learningRate = learningRates[i]
    accuracies = []

    for j in range(5):
        combineFoldName = combineFoldsNames[j]
        singleFoldName = singleFoldNames[j]
        X_train, y_train, num_features = read_libsvm(combineFoldName)
        X_test, y_test, _ = read_libsvm(singleFoldName, num_features)
        w,b = DecayingTheLearningRate(X_train.todense(), y_train, 10, learningRate, learningRate)
        accuracies.append(accuracy(X_test.todense(),y_test,w,b))

    sum = 0
    for j in range(5):
        sum += accuracies[j]
    average = sum / 5

    if average > bestAccuracy:
        bestLearningRate = learningRate
        bestAccuracy = average

print("Decaying The Learning Rate")
print("Best Learning Rate:",bestLearningRate)
print("Cross Validation Average Of Best Accuracy:",bestAccuracy)
print()
DecayingTheLearningRateHyperParameter = bestLearningRate

##########################################################################################################

printEpochs = True
print("Epochs")
print()

X_train, y_train, num_features = read_libsvm("data_train")
print("DecayingTheLearningRate")
iter, accur = DecayingTheLearningRate(X_train.todense(), y_train, 25, DecayingTheLearningRateHyperParameter, DecayingTheLearningRateHyperParameter)
print("Best Epochs:", iter, "Accuracy:", accur)
print()
DecayingTheLearningRateEpoch = iter - 1

printEpochs = False

##########################################################################################################

bestAccuracy = 0
bestB = 0
bestW = 0

print("Best of the Test")
print()

X_train, y_train, num_features = read_libsvm("data_train")
w,b = DecayingTheLearningRate(X_train.todense(), y_train, DecayingTheLearningRateEpoch, DecayingTheLearningRateHyperParameter, DecayingTheLearningRateHyperParameter)
X_test, y_test, _ = read_libsvm('data_test', num_features)
ac = accuracy(X_test.todense(),y_test,w,b)
if ac > bestAccuracy:
    bestB = b
    bestW = w
print("DecayingTheLearningRate Train Accuracy:", accuracy(X_train.todense(), y_train, w, b))
print("DecayingTheLearningRate Test Accuracy:",ac)

w = bestW
b = bestB
X_test, y_test, _ = read_libsvm('data_real', num_features)
with open('IDs') as csvfile:
    readCSV = csv.reader(csvfile, delimiter=',')
    with open('result.csv', mode='w') as employee_file:
        result_Writer = csv.writer(employee_file, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
        result_Writer.writerow(['example_id', 'label'])
        counter = 0
        X = X_test.todense()
        for row in readCSV:
            result_Writer.writerow([row[0],int(predict(X[counter],w,b))])
            counter = counter + 1