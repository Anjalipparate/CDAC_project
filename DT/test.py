import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier
from sklearn.preprocessing import MinMaxScaler

Arr_1 = pd.read_csv("data_group.csv",names=['Height','Weight','Index'])
print(Arr_1)

def days_late_xform(Index):
    if 7 <= Index <= 9: return '2'
    elif 4 <= Index <= 6: return '1'
    elif 1 <= Index <= 3: return '0'
    else: return 'None'

Arr_1["Label"] = Arr_1['Index'].map(days_late_xform)
print(Arr_1)
Arr_2 = pd.read_csv("data.csv",names=['Height','Weight'])
A = Arr_2.describe()

features = ['Height','Weight']
X = Arr_1[features]
y = Arr_1['Label']
print("X:\n",X)
print("Y:\n",y)
X_train, X_test,y_train, y_test = train_test_split(X, y,random_state =100)
print("X_train:\n",X_train)
print("y_train\n",y_train)
print("X-test\n",X_test)
print("y_test\n",y_test)

scaler = MinMaxScaler()
X_train = scaler.fit_transform(X_train)
X_test = scaler.transform(X_test)
clf=DecisionTreeClassifier().fit(X_train,y_train)
print('Accuracy of Decision Tree classifier on training set: {:.2f}'
     .format(clf.score(X_train, y_train)))
print('Accuracy of Decision Tree classifier on test set: {:.2f}'
     .format(clf.score(X_test, y_test)))

y_predict=clf.predict(X_test)
from sklearn.metrics import confusion_matrix

ppd=pd.DataFrame(confusion_matrix(y_test,y_predict))
print(ppd)
from sklearn.neighbors import KNeighborsClassifier

knn = KNeighborsClassifier()
knn.fit(X_train, y_train)
print('Accuracy of K-NN classifier on training set: {:.2f}'
     .format(knn.score(X_train, y_train)))
print('Accuracy of K-NN classifier on test set: {:.2f}'
     .format(knn.score(X_test, y_test)))
