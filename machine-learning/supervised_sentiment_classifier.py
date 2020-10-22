import pandas

from preprocess_text import preprocess_text

from sklearn.model_selection import train_test_split, cross_val_score, GridSearchCV
from sklearn.linear_model import SGDClassifier
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics import accuracy_score

def partion_data(sample, test_sample_size, columns):
    return train_test_split(sample[columns[1]], sample[columns[0]], 
        test_size=test_sample_size, random_state=123)

def classifier(data, columns, test_sample_size):
    sample = pandas.DataFrame(data, columns=columns)
    x_train, x_test, y_train, y_test = partion_data(sample, test_sample_size, columns)

    vectoriser = TfidfVectorizer(analyzer=preprocess_text)
    x_test = vectoriser.fit_transform(x_test)
    x_train = vectoriser.transform(x_train)

    sgd_clf = SGDClassifier(random_state=123)

    grid = {'fit_intercept': [True,False],
            'early_stopping': [True, False],
            'loss' : ['hinge', 'log', 'squared_hinge'],
            'penalty' : ['l2', 'l1', 'none']}
    search = GridSearchCV(estimator=sgd_clf, param_grid=grid, cv=5)
    search.fit(x_train, y_train)
    estimator = search.best_estimator_

    train_scores = cross_val_score(estimator, x_train, y_train, cv=5)

    y_predict = estimator.predict(x_test)
    test_scores = accuracy_score(y_test, y_predict)

    return train_scores, test_scores