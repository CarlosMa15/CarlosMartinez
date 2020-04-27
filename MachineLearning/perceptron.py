import numpy as np
import csv
from scipy.sparse import csr_matrix
from data.libsvm import read_libsvm
import numpy as np
import json
import matplotlib.pyplot as plt
from matplotlib.colors import ListedColormap
import matplotlib.animation as animation
from IPython.display import HTML

check1 = False
check2 = False
check3 = False
check4 = False

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

def DecayingTheLearningRatePredict(X, w, b,y,learningRate,t):
    activation = np.dot(X,w) + b
    if activation >= 0.0:
        return 1.0,w,b
    else:
        x = X.flat
        for i in range(len(w)):
            w[i] = w[i] + x[i]*learningRate*y

        # w = w + np.dot(X,learningRate*y)
        currentlearningRate = learningRate / (1 + t)
        b = b + currentlearningRate * y
        return -1.0,w,b

def DecayingTheLearningRateUpdate(x, y, w, b, lr, t):

    if y == 0:
        y = -1

    currentlr = lr / (1 +t)
    x = x.flat
    for i in range(len(w)):
        w[i] = w[i] + x[i] * currentlr * y

    b = b + currentlr * y

    return w, b

def SimplePerceptronPredictUpdate(x, y, w, b, lr):

    if y == 0:
        y = -1

    x = x.flat
    for i in range(len(w)):
        w[i] = w[i] + x[i] * lr * y

    b = b + lr * y

    return w, b

def SimplePerceptronPredict(X, w, b,y,learningRate):
    activation = np.dot(X,w) + b
    if activation >= 0.0:
        return 1.0,w,b
    else:
        x = X.flat
        for i in range(len(w)):
            w[i] = w[i] + x[i]*learningRate*y

        # w = w + np.dot(X,learningRate*y)
        b = b + learningRate * y
        return -1.0,w,b

def predict1(y):
    pos = 1
    neg = 0
    for i in y:
        if i == 1:
            pos = pos + 1
        else:
            neg = neg + 1

    if pos > neg:
        return 1
    else:
        return -1

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


def update(x, y, w, b, lr):
    # FILL IN
    # pass

    if y == 0:
        y = -1

    size = len(w)
    other = x.flat
    for i in range(size):
        w[i] = w[i] + lr * (y * other[i])

    b = b + lr * y
    return w, b

def shuffle_arrays(X, y):
    idx = np.arange(X.shape[0])
    np.random.shuffle(idx)
    return X[idx], y[idx]


def train(X_train, y_train, epochs, lr):
    # w = np.zeros(250, dtype = int)
    np.random.seed(1)
    w = np.random.uniform(-0.01, 0.01, size=X_train.shape[1])  # initialize w
    # w = np.random.uniform(0, 1, size=X_train.shape[1])
    b = 0  # initialize bias

    for epoch in range(epochs):
        x_shuffle, y_shuffle = shuffle_arrays(X_train, y_train)
        for x, y in zip(x_shuffle, y_shuffle):
            pred = predict(x,w,b)
            if pred != y:
                w,b = update(x,y,w,b,lr)
    return w, b

def train1(X_train, y_train, epochs, lr):
    # w = np.zeros(250, dtype = int)
    w = np.random.uniform(-0.01, 0.01, size=X_train.shape[1])  # initialize w
    # w = np.random.uniform(0, 1, size=X_train.shape[1])
    b = 0  # initialize bias

    for epoch in range(epochs):
        x_shuffle, y_shuffle = shuffle_arrays(X_train, y_train)
        for x, y in zip(x_shuffle, y_shuffle):
            pred = predict1(y_train)
            if pred != y:
                w,b = update(x,y,w,b,lr)
    return w, b

def SimplePerceptron(X_train, y_train, epochs, lr,learningRate):
    # w = np.zeros(250, dtype = int)
    np.random.seed(1)
    w = np.random.uniform(-0.01, 0.01, size=X_train.shape[1])  # initialize w
    # w = np.random.uniform(0, 1, size=X_train.shape[1])
    b = 0  # initialize bias
    bestE = 0
    bestER = 0
    count = 0

    for epoch in range(epochs):
        x_shuffle, y_shuffle = shuffle_arrays(X_train, y_train)
        for x, y in zip(x_shuffle, y_shuffle):
            # pred,w,b = SimplePerceptronPredict(x,w,b,y,learningRate)
            pred = predict(x, w, b)
            if pred != y:
                count = count + 1
                w,b = SimplePerceptronPredictUpdate(x,y,w,b,lr)
                # w,b = update(x,y,w,b,lr)

        if printEpochs:
            ac = printEpoch(epoch,X_train,y_train,w,b)
            if bestER < ac:
                bestER = ac
                bestE = epoch
    if printEpochs:
        return bestE + 1, bestER
    else:
        if check1:
            print("Number Of Updates:",count)
        return w, b

def DecayingTheLearningRate(X_train, y_train, epochs, lr,learningRate):
    # w = np.zeros(250, dtype = int)
    np.random.seed(1)
    w = np.random.uniform(-0.01, 0.01, size=X_train.shape[1])  # initialize w
    # w = np.random.uniform(0, 1, size=X_train.shape[1])
    b = 0  # initialize bias
    t = 0
    bestE = 0
    bestER = 0
    count = 0

    for epoch in range(epochs):
        x_shuffle, y_shuffle = shuffle_arrays(X_train, y_train)
        for x, y in zip(x_shuffle, y_shuffle):
            # pred,w,b = DecayingTheLearningRatePredict(x,w,b,y,learningRate, t)
            pred = predict(x, w, b)
            if pred != y:
                # w,b = update(x,y,w,b,lr)
                count = count + 1
                w,b = DecayingTheLearningRateUpdate(x, y, w, b, lr, t)
            #t = t + 1

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

def AveragedPerceptron(X_train, y_train, epochs, lr):
    # w = np.zeros(250, dtype = int)
    np.random.seed(1)
    w = np.random.uniform(-0.01, 0.01, size=X_train.shape[1])  # initialize w
    # w = np.random.uniform(0, 1, size=X_train.shape[1])
    wAverage = np.zeros(X_train.shape[1], dtype = int)
    bAverage = 0
    counter = 0
    b = 0  # initialize bias
    bestE = 0
    bestER = 0
    count = 0

    for epoch in range(epochs):
        x_shuffle, y_shuffle = shuffle_arrays(X_train, y_train)
        for x, y in zip(x_shuffle, y_shuffle):
            pred = predict(x,w,b)
            if pred != y:
                count = count + 1
                w,b = update(x,y,w,b,lr)
            wAverage = wAverage + w
            bAverage = bAverage + b
            counter = counter + 1
        if printEpochs:
            ac = printEpoch(epoch,X_train,y_train,wAverage / counter,bAverage / counter)
            if bestER < ac:
                bestER = ac
                bestE = epoch
    if printEpochs:
        return bestE + 1, bestER
    else:
        if check1:
            print("Number Of Updates:",count)
        return wAverage / counter, b /counter

def PocketPerceptron(X_train, y_train, epochs, lr):
    # w = np.zeros(250, dtype = int)
    np.random.seed(1)
    w = np.random.uniform(-0.01, 0.01, size=X_train.shape[1])  # initialize w
    # w = np.random.uniform(0, 1, size=X_train.shape[1])
    b = 0  # initialize bias
    wPocket = w
    bPocket = b
    wCount = 0
    pocketCount = 0
    bestE = 0
    bestER = 0
    count = 0

    for epoch in range(epochs):
        x_shuffle, y_shuffle = shuffle_arrays(X_train, y_train)
        for x, y in zip(x_shuffle, y_shuffle):
            pred = predict(x,w,b)
            # if pred == y:
            #     wCount = wCount + 1
            # pred1 = predict(x,wPocket,bPocket)
            # if pred1  == y:
            #     pocketCount = pocketCount + 1
            # if wCount > pocketCount:
            #     wPocket = w
            #     bPocket = b
            #     pocketCount = 0
            #     wCount = 0
            wCount = wCount + 1
            if pred != y:
                if wCount > pocketCount:
                    wPocket = w
                    bPocket = 0
                    pocketCount = wCount
                    wCount = 0
                count = count + 1
                w,b = update(x,y,w,b,lr)

        if printEpochs:
            ac = printEpoch(epoch,X_train,y_train,wPocket,bPocket)
            if bestER < ac:
                bestER = ac
                bestE = epoch
    if printEpochs:
        return bestE + 1, bestER
    else:
        if check1:
            print("Number Of Updates:",count)
        return wPocket, bPocket


def most_frequent(y):
    count = 0
    pos = 0
    neg = 0
    for i in y:
        count = count + 1
        if i == 1:
            pos = pos + 1
        else:
            neg = neg + 1

    if pos > neg:
        return pos / count
    else:
        return neg / count

##########################################################################################################

bestLearningRate = 0
bestAccuracy = 0
learningRates = [1, 0.1, 0.01]
combineFoldsNames = ["fold1234", "fold1235", "fold1245", "fold1345", "fold2345"]
singleFoldNames = ["fold5", "fold4", "fold3", "fold2", "fold1"]
SimplePerceptronHyperParameter = 0
DecayingTheLearningRateHyperParameter = 0
AveragedPerceptronHyperParameter = 0
PocketPerceptronHyperParameter = 0
SimplePerceptronEpoch = 0
DecayingTheLearningRateEpoch = 0
AveragedPerceptronEpoch = 0
PocketPerceptronEpoch = 0
printEpochs = False


X_train, y_train, num_features = read_libsvm('data_train')
X_test, y_test, _ = read_libsvm('data_test', num_features)

##########################################################################################################

print("Cross Validation")
print()
for i in range(3):
    learningRate = learningRates[i]
    accuracies = []

    for j in range(5):
        combineFoldName = combineFoldsNames[j]
        singleFoldName = singleFoldNames[j]
        X_train, y_train, num_features = read_libsvm(combineFoldName)
        X_test, y_test, _ = read_libsvm(singleFoldName, num_features)
        w,b = SimplePerceptron(X_train.todense(), y_train, 10, learningRate, learningRate)
        accuracies.append(accuracy(X_test.todense(),y_test,w,b))

    sum = 0
    for j in range(5):
        sum += accuracies[j]
    average = sum / 5

    if average > bestAccuracy:
        bestLearningRate = learningRate
        bestAccuracy = average

print("Simple Perceptron")
print("Best Learning Rate:",bestLearningRate)
print("Cross Validation Average Of Best Accuracy:",bestAccuracy)
print()
SimplePerceptronHyperParameter = bestLearningRate

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
        w,b = AveragedPerceptron(X_train.todense(), y_train, 10, learningRate)
        accuracies.append(accuracy(X_test.todense(),y_test,w,b))

    sum = 0
    for j in range(5):
        sum += accuracies[j]
    average = sum / 5

    if average > bestAccuracy:
        bestLearningRate = learningRate
        bestAccuracy = average

print("Averaged Perceptron")
print("Best Learning Rate:",bestLearningRate)
print("Cross Validation Average Of Best Accuracy:",bestAccuracy)
print()
AveragedPerceptronHyperParameter = bestLearningRate

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
        w,b = PocketPerceptron(X_train.todense(), y_train, 10, learningRate)
        accuracies.append(accuracy(X_test.todense(),y_test,w,b))

    sum = 0
    for j in range(5):
        sum += accuracies[j]
    average = sum / 5

    if average > bestAccuracy:
        bestLearningRate = learningRate
        bestAccuracy = average

print("Pocket Perceptron")
print("Best Learning Rate:",bestLearningRate)
print("Cross Validation Average Of Best Accuracy:",bestAccuracy)
print()
PocketPerceptronHyperParameter = bestLearningRate

##########################################################################################################

# print(SimplePerceptronHyperParameter)
# print(DecayingTheLearningRateHyperParameter)
# print(AveragedPerceptronHyperParameter)
# print(PocketPerceptronHyperParameter)
printEpochs = True

##########################################################################################################

# REMOVE THEY ARE ASSIGNED ABOVE
# FOR DEBUGGING PURPOSES
# SimplePerceptronHyperParameter = 0.1
# DecayingTheLearningRateHyperParameter = 1
# AveragedPerceptronHyperParameter = 0.01
# PocketPerceptronHyperParameter = 0.1

print("Epochs")
print()

X_train, y_train, num_features = read_libsvm("data_train")
print("Simple Perceptron")
iter, accur = SimplePerceptron(X_train.todense(), y_train, 20, SimplePerceptronHyperParameter, SimplePerceptronHyperParameter)
print("Best Epochs:", iter, "Accuracy:", accur)
print()
SimplePerceptronEpoch = iter - 1

X_train, y_train, num_features = read_libsvm("data_train")
print("DecayingTheLearningRate")
iter, accur = DecayingTheLearningRate(X_train.todense(), y_train, 20, DecayingTheLearningRateHyperParameter, DecayingTheLearningRateHyperParameter)
print("Best Epochs:", iter, "Accuracy:", accur)
print()
DecayingTheLearningRateEpoch = iter - 1

X_train, y_train, num_features = read_libsvm("data_train")
print("AveragedPerceptron")
iter, accur = AveragedPerceptron(X_train.todense(), y_train, 20, AveragedPerceptronHyperParameter)
print("Best Epochs:", iter, "Accuracy:", accur)
print()
AveragedPerceptronEpoch = iter - 1

X_train, y_train, num_features = read_libsvm("data_train")
print("PocketPerceptron")
iter, accur = PocketPerceptron(X_train.todense(), y_train, 20, AveragedPerceptronHyperParameter)
print("Best Epochs:", iter, "Accuracy:", accur)
print()
PocketPerceptronEpoch =iter - 1

printEpochs = False

##########################################################################################################

# REMOVE THEY ARE ASSIGNED ABOVE
# FOR DEBUGGING PURPOSES
# SimplePerceptronEpoch = 19
# DecayingTheLearningRateEpoch = 14
# AveragedPerceptronEpoch = 19
# PocketPerceptronEpoch = 10

bestAccuracy = 0
bestB = 0
bestW = 0
check1 = True
check2 = True
check3 = True
check4 =True
json_file = open("vocab_idx.json")
json_str = json_file.read()
vocab = json.loads(json_str)

print("Best of the Test")
print()

X_train, y_train, num_features = read_libsvm("data_train")
w,b = SimplePerceptron(X_train.todense(), y_train, SimplePerceptronEpoch, SimplePerceptronHyperParameter, SimplePerceptronHyperParameter)
X_test, y_test, _ = read_libsvm('data_test', num_features)
ac = accuracy(X_test.todense(),y_test,w,b)
if ac > bestAccuracy:
    bestB = b
    bestW = w
print("SimplePerceptron Train Accuracy:",accuracy(X_train.todense(),y_train,w,b))
print("SimplePerceptron Test Accuracy:",ac)

X_train, y_train, num_features = read_libsvm("data_train")
w,b = DecayingTheLearningRate(X_train.todense(), y_train, DecayingTheLearningRateEpoch, DecayingTheLearningRateHyperParameter, DecayingTheLearningRateHyperParameter)
X_test, y_test, _ = read_libsvm('data_test', num_features)
ac = accuracy(X_test.todense(),y_test,w,b)
if ac > bestAccuracy:
    bestB = b
    bestW = w
print("DecayingTheLearningRate Train Accuracy:", accuracy(X_train.todense(), y_train, w, b))
print("DecayingTheLearningRate Test Accuracy:",ac)

X_train, y_train, num_features = read_libsvm("data_train")
w,b = AveragedPerceptron(X_train.todense(), y_train, AveragedPerceptronEpoch, AveragedPerceptronHyperParameter)
X_test, y_test, _ = read_libsvm('data_test', num_features)
ac = accuracy(X_test.todense(),y_test,w,b)
if ac > bestAccuracy:
    bestB = b
    bestW = w
print("AveragedPerceptron Train Accuracy:",accuracy(X_train.todense(),y_train,w,b))
print("AveragedPerceptron Test Accuracy:",ac)

X_train, y_train, num_features = read_libsvm("data_train")
w,b = PocketPerceptron(X_train.todense(), y_train, PocketPerceptronEpoch, AveragedPerceptronHyperParameter)
X_test, y_test, _ = read_libsvm('data_test', num_features)
ac = accuracy(X_test.todense(),y_test,w,b)
if ac > bestAccuracy:
    bestB = b
    bestW = w
print("PocketPerceptron Train Accuracy:",accuracy(X_train.todense(),y_train,w,b))
print("PocketPerceptron Test Accuracy:",ac)
print()

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

# print("Top Words Of Best of the Best")
# print()
#
# X = np.argsort(bestW)[-10:]
# Y = np.argsort(bestW)[:10]
#
# print("Top 10:")
# for i in X:
#     print(vocab[str(i)])
# print()
#
# print("Bottom 10")
# for i in Y:
#     print(vocab[str(i)])