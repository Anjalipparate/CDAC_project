import pandas as pd
import numpy
import csv
set1=pd.read_csv("text2018.01.23.13.12.59.csv",names=['x','y','z','label'])
set0=pd.read_csv("text2018.01.24.20.11.14.csv",names=['x','y','z','label'])
testset=pd.read_csv("0-2018-01-25-18-51-10.csv",delim_whitespace=True,names=['x','y','z'])
print(testset)
print("Co-ordinates for 1\n",set1)
print("Co-ordinates for 0\n",set0)
print("\n")
dataset=set1
dataset=dataset.append(set0)
print(dataset,"\n")
features=['x','y','z']
X = dataset[features]
Y = dataset['label']

from sklearn.model_selection import train_test_split
xtrain,xtest,ytrain,ytest=train_test_split(X, Y,random_state=0)
print("X train\n",xtrain)
print("Y train\n",ytrain)
print("X test\n",xtest)
print("Y test\n",ytest)
predictthis=set0[features]
from sklearn.tree import DecisionTreeClassifier
model=DecisionTreeClassifier().fit(xtrain,ytrain)
pred=model.predict(testset)
print(pred)
