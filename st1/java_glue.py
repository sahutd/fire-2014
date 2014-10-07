from subprocess import Popen, PIPE


from ngram_extractor import character_ngram_extractor
file_ = '../preprocessed_data/hindi_words_in_hindi.txt'

ngrams_list = [character_ngram_extractor(i, file_) for i in range(1, 7)]


def get_hindi_words(word):
    query = ['java', 'Transliterator', word]
    proc = Popen(query, stdout=PIPE)
    proc.wait()
    words = [i.decode('utf-8') for i in proc.communicate()[0].split(b'\n') if i]
    return words


def predict(wordlist):
    max_word = ''
    max_word_score = -1
    for word in wordlist:
        current_score = 0
        for ngram in ngrams_list:
            for letter in word:
                if letter in ngram[0]:
                        current_score += 1
        if current_score > max_word_score:
            max_word_score = current_score
            max_word = word
    return max_word



if __name__ == '__main__':
    print(predict(get_hindi_words('tera')))
