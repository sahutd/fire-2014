"""
Author: Saimadhav Heblikar <saimadhavheblikar@gmail.com>
n-gram extraction tool
Uses nltk.
"""

import operator
from nltk.util import ngrams
from nltk import FreqDist


def character_ngram_extractor(n, file_='words.txt'):
    words = []
    ngram_list = []
    with open(file_) as f:  # words.txt in preprocessed_data folder
        content = f.read().lower().split('\n')
    words = ' '.join(content)
    n_gram = ngrams(words, n)  # the value of 'n' in n-gram
    fdist = FreqDist(n_gram)   # create frquency distribution
    freqd = sorted(fdist.iteritems(),
                   key=operator.itemgetter(1))  # sort on frequency
    for ngram, freq in freqd[::-1]:  # descending order
        """
        As we are dealing with collection of words rather than
        well formed natural sentences, it is safe(er) to ignore
        n-grams which have space in middle. Also ignore ngrams
        if they space both at start and end: they are just
        a (n-2)gram, and will create a lot of noise
        further down the line
        """
        temp = ''.join(ngram)
        if n == 1:
            if temp != ' ':
                ngram_list.append((temp, freq))
        elif n == 2:
            if ngram != ' ':
                ngram_list.append((temp, freq))
        else:
            if (' ' not in temp[1:-1]) and (temp.count(' ') <= 1):
                ngram_list.append((temp, freq))
    return ngram_list

if __name__ == '__main__':
    import sys
    if len(sys.argv) != 3:
        print 'Format: python ngram_extractor.py <input-file> n'
        sys.exit(0)
    else:
        file_ = sys.argv[1]
        n = int(sys.argv[2])
        for ngram, freq in character_ngram_extractor(n, file_):
            print ''.join(ngram), ":", freq
