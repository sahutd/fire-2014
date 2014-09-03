"""
An example classification workflow based on scikit-learn
"""
import random

import numpy as np
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.naive_bayes import MultinomialNB
from sklearn.metrics import classification_report
from sklearn import metrics

ENGLISH = 0
HINDI = 1


def get_words_with_labels():
    """
    Return tuple (word_data, word_labels)
    word_data - list of words
    word_labels - list of labels corresponding to word in word_data
                  0 indicates english
                  1 indicates hindi
    """
    hindi_words_file = '../preprocessed_data/hindi_words_in_english.txt'
    english_words_file = '../preprocessed_data/english_words_in_english.txt'
    data = []
    labels = []
    temp = []

    for file_, language_label in [(english_words_file, ENGLISH),
                                  (hindi_words_file, HINDI)]:
        with open(file_) as f:
            words = f.read().split('\n')  # tokenize
            for w in words:
                temp.append((w, language_label))
    random.shuffle(temp)
    for word, label in temp:
        data.append(word)
        labels.append(label)

    return data, labels


def split_into_training_test(data, labels):
    """
    Return tuple(X_train, Y_train, X_test, Y_test)
    Training and test data is split in the ration 80:20
    """
    train_size = int(len(data) * 0.80)
    X_train = np.array([''.join(w) for w in data[0:train_size]])
    Y_train = np.array([w for w in labels[0:train_size]])

    X_test = np.array([''.join(w) for w in data[train_size + 1:]])
    Y_test = np.array([w for w in labels[train_size + 1:]])

    return X_train, Y_train, X_test, Y_test


def get_vectorizer():
    """
    Return a CountVectorizer which works on:
    Character n-gram with word boundries, with ngram range from 1 to 6
    """
    return CountVectorizer(ngram_range=(1, 6), analyzer='char_wb')


def get_classifier(x_train, y_train):
    """
    Return a Multinomial naive bayes classifier which is trained on
    x_train and y_train data
    x_train is usually words
    y_train is usually language_labels
    """
    return MultinomialNB().fit(x_train, y_train)


if __name__ == '__main__':
    vec = get_vectorizer()
    data, labels = get_words_with_labels()
    X_train, Y_train, X_test, Y_test = split_into_training_test(data, labels)
    # Transform step
    X_train = vec.fit_transform(X_train)
    X_test = vec.transform(X_test)

    classifier = get_classifier(X_train, Y_train)

    # predict using classifier for the remaining 20% of the dataset
    prediction = classifier.predict(X_test)
    # display results
    print(classification_report(Y_test, prediction))
    print("the accuarcy:  ", str(metrics.accuracy_score(Y_test, prediction)))

    # classify any word you want here
    x_test = words_ = ['hum', 'you', 'me', 'where', 'thumara', 'tera']
    x_test = vec.transform(x_test)

    print('-'*40)

    prediction = classifier.predict(x_test)
    for word, prediction in zip(words_, prediction):
        print(word, "ENGLISH" if prediction == ENGLISH else "HINDI")
