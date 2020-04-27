'''
Created on Mar 17, 2019
@author: Carlos Martinez
'''
from scipy.cluster.hierarchy import fcluster, linkage
import scipy.cluster.vq as sc
import matplotlib.pyplot as plt
from scipy.spatial import distance
import math
import pandas as pd
import numpy as np
from sklearn.decomposition import PCA as sklearnPCA
from adjustText import adjust_text

from pandas import DataFrame
from sklearn.cluster import KMeans

def get_coords(filename, delim):
    listVecs = []
    lstVals = []
    with open(filename) as fl:
        for line in fl:
            lstVals = [float(i) for i in line.split(delim)]
            #lstVals = lstVals[1:]       #remove first element from list
            listVecs.append(lstVals)
    return listVecs

dataset2 = get_coords("data2.txt"," ")

#kmeans = KMeans(n_clusters=4).fit(df)
#centroids = kmeans.cluster_centers_
#print(centroids)

#plt.scatter(df['x'], df['y'], c=kmeans.labels_.astype(float), s=50, alpha=0.5)
#plt.scatter(centroids[:, 0], centroids[:, 1], c='red', s=50)

#plt.plot()
#plt.title('k means centroids')



def plotReduce(X):
    #X_norm = (X - X.min()) / (X.max() - X.min())
    pca = sklearnPCA(n_components=2)  # 2-dimensional PCA
    transformed = pca.fit_transform(X)
    return transformed

transformed = plotReduce(dataset2)
#Xvals = transformed[:,0]
#Yvals = transformed[:,1]
#plt.scatter(Xvals, Yvals)
#plt.show()
#center_kpp, idx = sc.kmeans2(np.array(transformed), k=4, minit='random', iter=100)
#kpp_center_set = np.array(center_kpp)
#plt.scatter(kpp_center_set[:,0], kpp_center_set[:,1], marker="x", color='r')

labels = ["Cyprus",
"Portugal",
"Iceland",
"Malta",
"Sweden",
"Netherlands",
"Austria",
"Latvia",
"Ireland",
"Luxembourg",
"Poland",
"CzechRepublic",
"Slovenia",
"France",
"Bulgaria",
"Lithuania",
"Croatia",
"Romania",
"Hungary",
"Spain",
"Belgium",
"Norway",
"UnitedKingdom",
"Finland",
"Denmark",
"Italy",
"Germany",
"Estonia"]

kmeans = KMeans(n_clusters=4, init='k-means++', n_init=100)
kmeans.fit(transformed)
y_kmeans = kmeans.predict(transformed)

for i, label in enumerate(labels):
    x = transformed[i,0]
    y = transformed[i,1]
    #plt.scatter(x, y, marker='x', color='red')
    #plt.text(x+0.3, y+0.3, label, fontsize=9)
#plt.show()

fig, ax = plt.subplots()
ax.scatter(transformed[:, 0], transformed[:, 1], c=y_kmeans, s=50, cmap='viridis')
texts = []
for i, txt in enumerate(labels):
    texts.append(ax.text(transformed[i,0], transformed[i,1], txt))
adjust_text(texts)

centers = kmeans.cluster_centers_
plt.scatter(centers[:, 0], centers[:, 1], c='black', s=200, alpha=0.5)
#plt.figure(figsize=(800/140, 800/140), dpi=140)
plt.savefig('C:\\Users\\igor.ivanov\\Desktop\\my_fig.pdf')
plt.show()