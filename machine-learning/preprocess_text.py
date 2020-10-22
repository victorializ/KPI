import nltk

nltk.download('wordnet')
nltk.download('stopwords') 

from nltk.corpus import stopwords
from nltk.stem import WordNetLemmatizer
from nltk.tokenize import RegexpTokenizer

def tokenise(text):
    word_character = r'[a-zA-Z]+'
    tokeniser = RegexpTokenizer(word_character)
    return tokeniser.tokenize(text)

def lemmatiser(tokens):
    lemmatiser = WordNetLemmatizer()
    lemmas = [lemmatiser.lemmatize(token.lower(), pos='v') for token in tokens]
    return lemmas
    
def remove_stop_words(tokens):
    keywords = [token for token in tokens if token not in stopwords.words('english')]
    return keywords

def preprocess_text(text):
    return remove_stop_words(
        lemmatiser(
            tokenise(text)
        )
    )