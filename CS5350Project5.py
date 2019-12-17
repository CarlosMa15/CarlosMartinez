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
        return 1 # 1.0
    else:
        return 0 # -1.0

def accuracy(X, y, w, b):
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
    np.random.seed(20)
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
            if y == 0:
                y = -1

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

##########################################################################################################

bestLearningRate = 0
bestAccuracy = 0
learningRates = [10,1,0.1, 0.01,0.001,0.0001]
combineFoldsNames = ["fold1234", "fold1235", "fold1245", "fold1345", "fold2345"]
singleFoldNames = ["fold5", "fold4", "fold3", "fold2", "fold1"]
DecayingTheLearningRateHyperParameter = 0
DecayingTheLearningRateEpoch = 0
printEpochs = False


print("Cross Validation")
print()
C = learningRates[0]
for i in range(6):
    learningRate = learningRates[i]
    accuracies = []

    for j in range(5):
        combineFoldName = combineFoldsNames[j]
        singleFoldName = singleFoldNames[j]
        X_train, y_train, num_features = read_libsvm(combineFoldName)
        X_test, y_test, _ = read_libsvm(singleFoldName, num_features)
        w,b = SimplePerceptron(X_train.todense(), y_train, 10, learningRate, C)
        accuracies.append(accuracy(X_test.todense(),y_test,w,b))
        print("Cal")

    sum = 0
    for j in range(5):
        sum += accuracies[j]
    average = sum / 5
    print("Learning Rate:",learningRate,"Average: ",average,"C:",C)

    if average > bestAccuracy:
        bestLearningRate = learningRate
        BestC = C
        bestAccuracy = average

C = learningRates[1]
for i in range(6):
    learningRate = learningRates[i]
    accuracies = []

    for j in range(5):
        combineFoldName = combineFoldsNames[j]
        singleFoldName = singleFoldNames[j]
        X_train, y_train, num_features = read_libsvm(combineFoldName)
        X_test, y_test, _ = read_libsvm(singleFoldName, num_features)
        w,b = SimplePerceptron(X_train.todense(), y_train, 10, learningRate, C)
        accuracies.append(accuracy(X_test.todense(),y_test,w,b))
        print("Cal")

    sum = 0
    for j in range(5):
        sum += accuracies[j]
    average = sum / 5
    print("Learning Rate:",learningRate,"Average: ",average,"C:",C)

    if average > bestAccuracy:
        bestLearningRate = learningRate
        BestC = C
        bestAccuracy = average

C = learningRates[2]
for i in range(6):
    learningRate = learningRates[i]
    accuracies = []

    for j in range(5):
        combineFoldName = combineFoldsNames[j]
        singleFoldName = singleFoldNames[j]
        X_train, y_train, num_features = read_libsvm(combineFoldName)
        X_test, y_test, _ = read_libsvm(singleFoldName, num_features)
        w,b = SimplePerceptron(X_train.todense(), y_train, 10, learningRate, C)
        accuracies.append(accuracy(X_test.todense(),y_test,w,b))
        print("Cal")

    sum = 0
    for j in range(5):
        sum += accuracies[j]
    average = sum / 5
    print("Learning Rate:",learningRate,"Average: ",average,"C:",C)

    if average > bestAccuracy:
        bestLearningRate = learningRate
        BestC = C
        bestAccuracy = average

C = learningRates[3]
for i in range(6):
    learningRate = learningRates[i]
    accuracies = []

    for j in range(5):
        combineFoldName = combineFoldsNames[j]
        singleFoldName = singleFoldNames[j]
        X_train, y_train, num_features = read_libsvm(combineFoldName)
        X_test, y_test, _ = read_libsvm(singleFoldName, num_features)
        w,b = SimplePerceptron(X_train.todense(), y_train, 10, learningRate, C)
        accuracies.append(accuracy(X_test.todense(),y_test,w,b))
        print("Cal")

    sum = 0
    for j in range(5):
        sum += accuracies[j]
    average = sum / 5
    print("Learning Rate:",learningRate,"Average: ",average,"C:",C)

    if average > bestAccuracy:
        bestLearningRate = learningRate
        BestC = C
        bestAccuracy = average

C = learningRates[4]
for i in range(6):
    learningRate = learningRates[i]
    accuracies = []

    for j in range(5):
        combineFoldName = combineFoldsNames[j]
        singleFoldName = singleFoldNames[j]
        X_train, y_train, num_features = read_libsvm(combineFoldName)
        X_test, y_test, _ = read_libsvm(singleFoldName, num_features)
        w,b = SimplePerceptron(X_train.todense(), y_train, 10, learningRate, C)
        accuracies.append(accuracy(X_test.todense(),y_test,w,b))
        print("Cal")

    sum = 0
    for j in range(5):
        sum += accuracies[j]
    average = sum / 5
    print("Learning Rate:",learningRate,"Average: ",average,"C:",C)

    if average > bestAccuracy:
        bestLearningRate = learningRate
        BestC = C
        bestAccuracy = average

C = learningRates[5]
for i in range(6):
    learningRate = learningRates[i]
    accuracies = []

    for j in range(5):
        combineFoldName = combineFoldsNames[j]
        singleFoldName = singleFoldNames[j]
        X_train, y_train, num_features = read_libsvm(combineFoldName)
        X_test, y_test, _ = read_libsvm(singleFoldName, num_features)
        w,b = SimplePerceptron(X_train.todense(), y_train, 10, learningRate, C)
        accuracies.append(accuracy(X_test.todense(),y_test,w,b))
        print("Cal")

    sum = 0
    for j in range(5):
        sum += accuracies[j]
    average = sum / 5
    print("Learning Rate:",learningRate,"Average: ",average,"C:",C)

    if average > bestAccuracy:
        bestLearningRate = learningRate
        BestC = C
        bestAccuracy = average

print("Simple Perceptron")
print("Best C:",BestC)
print("Best Learning Rate:",bestLearningRate)
print("Cross Validation Average Of Best Accuracy:",bestAccuracy)
print()
SimplePerceptronHyperParameter = bestLearningRate
printEpochs = True

##########################################################################################################

print("Epochs")
print()

X_train, y_train, num_features = read_libsvm("data_train")
print("Simple Perceptron")
iter, accur = SimplePerceptron(X_train.todense(), y_train, 20, SimplePerceptronHyperParameter, BestC)
print("Best Epochs:", iter, "Accuracy:", accur)
print()
SimplePerceptronEpoch = iter - 1

printEpochs = False

##########################################################################################################


print("Best of the Test")
print()

X_train, y_train, num_features = read_libsvm("data_train")
w,b = SimplePerceptron(X_train.todense(), y_train, SimplePerceptronEpoch, SimplePerceptronHyperParameter, BestC)
#w,b = SimplePerceptron(X_train.todense(), y_train, 17, 0.00001, 0.01)
X_test, y_test, _ = read_libsvm('data_test', num_features)
ac = accuracy(X_test.todense(),y_test,w,b)
print(ac)

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
