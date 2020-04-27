from builtins import range

import numpy as np
import csv
from scipy.sparse import csr_matrix
from libsvm import read_libsvm
import numpy as np
import json
import numpy as np
import math
DATA_DIR = 'data/'
import random
import string
import math
# X_test, y_test, _ = read_libsvm('data_test', num_features)
# X_train.todense()[0] get row
# X_train.todense()[0].flat[0] get colomn item

def totalPositive(Y):
    total = 0
    for i in Y:
        if i == 1:
            total = total +1
    return total

LambdaTerms = [2, 1.5, 1.0, 0.5]
combineFoldsNames = ["fold1234", "fold1235", "fold1245", "fold1345", "fold2345"]
singleFoldNames = ["fold5", "fold4", "fold3", "fold2", "fold1"]
bestaccuracy = 0
bestLambda = 2

for i in range(4):
    lambdaTerm = LambdaTerms[i]
    accuracies = []
    for j in range(5):
        combineFoldName = combineFoldsNames[j]
        singleFoldName = singleFoldNames[j]

        X_train, y_train, num_features = read_libsvm(combineFoldName)
        totalCol = len(X_train.todense()[1].flat)
        totalRows = len(X_train.todense())
        totalOnes = totalPositive(y_train)
        totalOnesProb = totalOnes / len(y_train)
        positiveOnes = []
        negativeOnes = []
        for i in range(totalCol):
            OnesPos = 0
            OnesNeg = 0
            for j in range(totalRows):
                x = X_train.todense()[j].flat[i]
                if x == 1:
                    if y_train[j] == 1:
                        OnesPos = OnesPos + 1
                    else:
                        OnesNeg = OnesNeg + 1

            positiveOnes.append((OnesPos + lambdaTerm) / (totalOnes + (2 * lambdaTerm)))
            negativeOnes.append((OnesNeg + lambdaTerm) / ((totalRows - totalOnes) + (2 * lambdaTerm)))

        X_train, y_train, num_features = read_libsvm(singleFoldName)
        totalCol = len(X_train.todense()[1].flat)
        totalRows = len(X_train.todense())
        correct = 0
        for i in range(totalRows):
            posListGivenLabel = 0
            negListGivenLabel = 0
            for j in range(totalCol):
                x = X_train.todense()[i].flat[j]
                if x == 1:
                    posListGivenLabel = posListGivenLabel + math.log(positiveOnes[j])
                    negListGivenLabel = negListGivenLabel + math.log(negativeOnes[j])
                else:
                    posListGivenLabel = posListGivenLabel + math.log(1 - positiveOnes[j])
                    negListGivenLabel = negListGivenLabel + math.log(1 - negativeOnes[j])

            # posProb = posListGivenLabel * totalOnesProb
            # negProb = negListGivenLabel * (1 - totalOnesProb)
            posProb = posListGivenLabel + math.log(totalOnesProb)
            negProb = negListGivenLabel + math.log((1 - totalOnesProb))
            prediction = 1
            if negProb > posProb:
                prediction = -1
            if y_train[i] == prediction:
                correct = correct + 1

        thisAccuracy =correct / totalRows
        print("Acc:",thisAccuracy,lambdaTerm)
        accuracies.append(thisAccuracy)

    sum = 0
    for j in range(5):
        sum += accuracies[j]
    average = sum / 5

    if average > bestaccuracy:
        bestLambda = lambdaTerm
        bestaccuracy = average

    print("Average:",average,lambdaTerm)

print("Best:",bestaccuracy,bestLambda)

X_train, y_train, num_features = read_libsvm('hand_data_train')

###################################################################################################
totalCol = len(X_train.todense()[1].flat)
totalRows = len(X_train.todense())
totalOnes = totalPositive(y_train)
totalOnesProb = totalOnes / len(y_train)
positiveOnes = []
negativeOnes =[]
lambdaTerm = bestLambda
for i in range(totalCol):
    OnesPos = 0
    OnesNeg = 0
    for j in range(totalRows):
        x = X_train.todense()[j].flat[i]
        if x == 1:
            if y_train[j] == 1:
                OnesPos = OnesPos + 1
            else:
                OnesNeg = OnesNeg + 1

    positiveOnes.append((OnesPos + lambdaTerm)/ (totalOnes + (2 * lambdaTerm)) )
    negativeOnes.append((OnesNeg + lambdaTerm)/ ((totalRows - totalOnes) + (2 * lambdaTerm)) )

#################################################################################################
correct = 0
for i in range(totalRows):
    posListGivenLabel = 0
    negListGivenLabel = 0
    for j in range(totalCol):
        x = X_train.todense()[i].flat[j]
        if x == 1:
            posListGivenLabel = posListGivenLabel + math.log(positiveOnes[j])
            negListGivenLabel = negListGivenLabel + math.log(negativeOnes[j])
        else:
            posListGivenLabel = posListGivenLabel + math.log(1 - positiveOnes[j])
            negListGivenLabel = negListGivenLabel + math.log(1 - negativeOnes[j])

    # posProb = posListGivenLabel * totalOnesProb
    # negProb = negListGivenLabel * (1 - totalOnesProb)
    posProb = posListGivenLabel + math.log(totalOnesProb)
    negProb = negListGivenLabel + math.log((1 - totalOnesProb))
    prediction = 1
    if negProb > posProb:
        prediction = -1
    if y_train[i] == prediction:
        correct = correct + 1
print("Train",correct / totalRows)
#################################################################################################

X_train, y_train, num_features = read_libsvm('hand_data_test')
totalCol = len(X_train.todense()[1].flat)
totalRows = len(X_train.todense())

correct = 0
for i in range(totalRows):
    posListGivenLabel = 0
    negListGivenLabel = 0
    for j in range(totalCol):
        x = X_train.todense()[i].flat[j]
        if x == 1:
            posListGivenLabel = posListGivenLabel + math.log(positiveOnes[j])
            negListGivenLabel = negListGivenLabel + math.log(negativeOnes[j])
        else:
            posListGivenLabel = posListGivenLabel + math.log(1 - positiveOnes[j])
            negListGivenLabel = negListGivenLabel + math.log(1 - negativeOnes[j])

    # posProb = posListGivenLabel * totalOnesProb
    # negProb = negListGivenLabel * (1 - totalOnesProb)
    posProb = posListGivenLabel + math.log(totalOnesProb)
    negProb = negListGivenLabel + math.log((1 - totalOnesProb))
    prediction = 1
    if negProb > posProb:
        prediction = -1
    if y_train[i] == prediction:
        correct = correct + 1
print("Test",correct / totalRows)