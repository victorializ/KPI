import gzip
import json

from supervised_sentiment_classifier import classifier

def read_data(source_file):
    text_format = 'rt'
    return gzip.open(source_file, text_format).read()

def prepare_data(text, samples_number, columns, separator='\n'):
    reviews = []
    for value in text.split(separator)[:samples_number]:
        value = json.loads(value)    
        if(int(value[columns[0]]) > 3):
            reviews.append((1, value[columns[1]]))
        elif(int(value[columns[0]]) > 0):
            reviews.append((0, value[columns[1]]))
    return reviews

def log(scores):
    print("Accuracy: %0.2f" % scores.mean(), 
        "(+/- %0.2f)" % (scores.std() * 2) if scores.std() else '')

samples_number = 1000
test_sample_size = 0.3
source_file = 'goodreads_reviews.json.gz' #https://sites.google.com/eng.ucsd.edu/ucsdbookgraph/reviews?authuser=0ss
columns = ['rating', 'review_text']

data = prepare_data(read_data(source_file), samples_number, columns)
train_scores, test_scores = classifier(data, columns, test_sample_size)
log(train_scores)
log(test_scores)