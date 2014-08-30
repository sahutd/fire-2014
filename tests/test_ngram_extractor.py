import unittest
import os
from tempfile import NamedTemporaryFile
from ..utils import ngram_extractor

cne = ngram_extractor.character_ngram_extractor


class TestNGramExtractor(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        pass

    def setUp(self):
        self.file_ = NamedTemporaryFile(delete=False).name
        with open(self.file_, 'w') as file_:
            file_.write('ab acc aba e')

    def tearDown(self):
        os.unlink(self.file_)

    def test_zero_negative_gram(self):
        with self.assertRaises(ValueError) as ve:
            cne(0, file_=self.file_)
            self.assertIn('positive', str(ve))

        with self.assertRaises(ValueError) as ve:
            cne(-1, file_=self.file_)
            self.assertIn('positive', str(ve))

    def test_non_integer_gram(self):
        with self.assertRaises(ValueError) as ve:
            cne(1.2, file_=self.file_)
            self.assertIn('integer', str(ve))

    def test_one_gram(self):
        result = cne(1, file_=self.file_)
        self.assertListEqual([('a', 4), ('b', 2), ('c', 2), ('e', 1)], result)

    def test_two_gram(self):
        result = cne(2, file_=self.file_)
        expected = [('ab', 2), (' a', 2), ('cc', 1), (' e', 1),
                    ('c ', 1), ('ba', 1), ('b ', 1), ('a ', 1), ('ac', 1)]
        self.assertListEqual(sorted(expected), sorted(result))

    def test_three_gram(self):
        result = cne(3, file_=self.file_)
        expected = [(' ab', 1), (' ac', 1), ('ab ', 1), ('aba', 1), ('acc', 1),
                    ('ba ', 1), ('cc ', 1)]
        self.assertListEqual(sorted(expected), sorted(result))


if __name__ == '__main__':
    unittest.main()
