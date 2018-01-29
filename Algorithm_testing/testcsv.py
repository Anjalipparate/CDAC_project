import pandas as pd
from numpy import array as ar

set10=pd.read_csv("1-2018-01-28-23-41-13.csv",delim_whitespace=True,names=['x','y','z','label'])
set11=pd.read_csv("1-2018-01-29-11-29-13.csv",delim_whitespace=True,names=['x','y','z','label'])
set12=pd.read_csv("1-2018-01-29-11-31-07.csv",delim_whitespace=True,names=['x','y','z','label'])
set13=pd.read_csv("1-2018-01-29-11-31-51.csv",delim_whitespace=True,names=['x','y','z','label'])
set14=pd.read_csv("1-2018-01-29-11-31-54.csv",delim_whitespace=True,names=['x','y','z','label'])
set00=pd.read_csv("0-2018-01-29-11-30-04.csv",delim_whitespace=True,names=['x','y','z','label'])
set01=pd.read_csv("0-2018-01-29-11-29-19.csv",delim_whitespace=True,names=['x','y','z','label'])
set02=pd.read_csv("0-2018-01-28-23-40-33.csv",delim_whitespace=True,names=['x','y','z','label'])
set03=pd.read_csv("0-2018-01-29-11-31-44.csv",delim_whitespace=True,names=['x','y','z','label'])
set04=pd.read_csv("0-2018-01-29-11-32-13.csv",delim_whitespace=True,names=['x','y','z','label'])
testset=pd.read_csv("1-2018-01-29-12-16-48.csv",delim_whitespace=True,names=['x','y','z'])
print(testset)
"""print("Co-ordinates for 1\n",set1)
print("Co-ordinates for 0\n",set0)"""
print("\n")
dataset=set10
dataset=dataset.append(set11)
dataset=dataset.append(set12)
dataset=dataset.append(set13)
dataset=dataset.append(set14)
dataset=dataset.append(set00)
dataset=dataset.append(set01)
dataset=dataset.append(set02)
dataset=dataset.append(set03)
dataset=dataset.append(set04)
print(dataset,"\n")
features=['x','y','z']
X = dataset[features]
Y = dataset['label']

from sklearn.model_selection import train_test_split
xtrain,xtest,ytrain,ytest=train_test_split(X, Y,test_size=0.2)
print("X train\n",xtrain)
print("Y train\n",ytrain)
print("X test\n",xtest)
print("Y test\n",ytest)
#predictthis=set0[features]
from sklearn.tree import DecisionTreeClassifier
modelD=DecisionTreeClassifier().fit(xtrain,ytrain)
print("Accuracy on Training set DecisionTreeClassifier:",modelD.score(xtrain,ytrain))
print("Accuracy on Test set DecisionTreeClassifier:",modelD.score(xtest,ytest))
pred=modelD.predict(testset)
print(pred)
from sklearn.neighbors import KNeighborsClassifier
modelKnn=KNeighborsClassifier().fit(xtrain,ytrain)
print("Accuracy on Training set KNeighborsClassifier:",modelKnn.score(xtrain,ytrain))
print("Accuracy on Test set KNeighborsClassifier:",modelKnn.score(xtest,ytest))
pred=modelKnn.predict(testset)
print(pred)
from sklearn.linear_model import LogisticRegression
modelLR=LogisticRegression().fit(xtrain,ytrain)
print("Accuracy on Training set LogisticRegression:",modelLR.score(xtrain,ytrain))
print("Accuracy on Test set LogisticRegression:",modelLR.score(xtest,ytest))

from sklearn.svm import SVC
modelSVC=SVC().fit(xtrain,ytrain)
print("Accuracy on Training set Support Vector Machine:",modelSVC.score(xtrain,ytrain))
print("Accuracy on Test set Support Vector Machine:",modelSVC.score(xtest,ytest))

from sklearn.discriminant_analysis import LinearDiscriminantAnalysis
modelLDA=LinearDiscriminantAnalysis().fit(xtrain,ytrain)
print("Accuracy on Training set LinearDiscriminantAnalysis:",modelLDA.score(xtrain,ytrain))
print("Accuracy on Test set LinearDiscriminantAnalysis:",modelLDA.score(xtest,ytest))

from sklearn.naive_bayes import GaussianNB
modelGNB=GaussianNB().fit(xtrain,ytrain)
print("Accuracy on Training set GaussianNB:",modelGNB.score(xtrain,ytrain))
print("Accuracy on Test set GaussianNB:",modelGNB.score(xtest,ytest))

resultsTest=[modelD.score(xtest,ytest),modelKnn.score(xtest,ytest),modelLR.score(xtest,ytest),modelSVC.score(xtest,ytest),modelLDA.score(xtest,ytest),modelGNB.score(xtest,ytest)]
name=['DT','KNN','LR','SVM','LDA','GNB']
resultsTrain=[modelD.score(xtrain,ytrain),modelKnn.score(xtrain,ytrain),modelLR.score(xtrain,ytrain),modelSVC.score(xtrain,ytrain),modelLDA.score(xtrain,ytrain),modelGNB.score(xtrain,ytrain)]
#results=ar(results)
#names=ar(names)
d1={'Algorithm':name,'Accuracy':resultsTrain}
df1=pd.DataFrame(data=d1)
print(dataset.describe())
d={'Algorithm':name,'Accuracy':resultsTest}
df=pd.DataFrame(data=d)
import matplotlib.pyplot as plt
plt.subplot(121)
x=ar([0,1,2,3,4,5])
y=ar(df['Accuracy'])
plt.xticks(x,name)
plt.bar(x,y)
plt.ylabel('Accuracy')
plt.xlabel('Algorithms')
plt.title('Test Set Accuracy')
plt.subplot(122)
x=ar([0,1,2,3,4,5])
y=ar(df1['Accuracy'])
plt.xticks(x,name)
plt.bar(x,y)
plt.ylabel('Accuracy')
plt.xlabel('Algorithms')
plt.title('Training Set Accuracy')
#pl.bar(kind='bar')
plt.show()
