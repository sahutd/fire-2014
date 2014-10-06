"""
An example classification workflow based on scikit-learn
"""
import random
import sys

import numpy as np
from sklearn.feature_extraction.text import CountVectorizer as Vectorizer
from sklearn.naive_bayes import MultinomialNB as Classifier

from java_glue import predict, get_hindi_words

ENGLISH = 0
ENGLISH_SUFFIX = r'\E'
HINDI = 1
HINDI_SUFFIX = r'\H'


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
    X = np.array([''.join(w) for w in data])
    Y = np.array([w for w in labels])
    return X, Y


def get_vectorizer():
    """
    Return a CountVectorizer which works on:
    Character n-gram with word boundries, with ngram range from 1 to 6
    """
    return Vectorizer(ngram_range=(1, 6), analyzer='char_wb')


def get_classifier(x_train, y_train):
    """
    Return a Multinomial naive bayes classifier which is trained on
    x_train and y_train data
    x_train is usually words
    y_train is usually language_labels
    """
    return Classifier().fit(x_train, y_train)


def classify_data(vectorizer, classifier, output_file=sys.stdout, query=None):
    if not query:
        input_file = sys.argv[1]
        with open(input_file) as f:
            source = list(map(str.split, f.read().splitlines()))
    else:
        source = query

    for line in source:
        words = words_ = [i.lower() for i in line]
        words_ = vec.transform(words_)
        prediction = classifier.predict(words_)
        output = []
        for w, p in zip(words, prediction):
            suffix = HINDI_SUFFIX if p == HINDI else ENGLISH_SUFFIX
            if p == HINDI:
                suffix = suffix + predict(get_hindi_words(w)) + ' '
            output.append(w + suffix)
        print(' '.join(output), file=output_file)

vec = get_vectorizer()
data, labels = get_words_with_labels()
X, Y = split_into_training_test(data, labels)
# Transform step
X = vec.fit_transform(X)

classifier = get_classifier(X, Y)


def interface():
    while True:
        sentence = input()
        classify_data(vec, classifier, sys.stdout, [[sentence]])

if __name__ == '__main__':
    #if len(sys.argv) != 2:
        #raise ValueError("Input format: python classifier.py <input-file>")
    #classify_data(vec, classifier)
    interface()
