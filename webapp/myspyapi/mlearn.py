from __future__ import unicode_literals
import pandas as pd 
import numpy as np 
from sklearn.ensemble import RandomForestClassifier



#farzi
import sqlite3
cnx = sqlite3.connect('/home/gaurav/cfd18/webapp/db.sqlite3')
df = pd.read_sql_query("SELECT * FROM Locationdata", cnx)

def random_forest(wifi_string, building_name):
	global df
	try:
		dfpart = df[df['building']==building_name]['wifi'].apply(lambda x: map(int, x.split(',')))
		dfpart = dfpart.apply(pd.Series)
		del df['wifi']
		df = pd.concat([df, dfpart], axis=1)
		wifi_int = pd.DataFrame(map(int, wifi_string.split(','))).transpose()
		#print wifi_int
		dff = df[df['building'] == building_name]
	#dfpart = dfpart[dfpart['building'] == building_name]
	except:
		return {"Error": "This building has not been added to the database. Please use the Learn feature to tag positions"}
	trainx = dfpart
	trainy = dff['room']
	#print dfpart
	#print dff['room']
	try:
		clf = RandomForestClassifier(n_estimators=500, n_jobs = -1)
		clf.fit(trainx, trainy)
		class_list = clf.predict(wifi_int)
		class_prob = clf.predict_proba(wifi_int)
		return_d = {}
		#print class_list
		#print class_prob
	except:
		return {"Error": "Random Forest Classifier got an unexpected error"}

	try:
		for i in xrange(min(3, len(class_list))):
			return_d[class_list[i]] = class_prob[i][0]
		return return_d
	except:
		return {"Error": "Error in building location object for the response"}

#test
#random_forest('-47,-42,-120,-120,-120,-120,-120,-120,-120,-120,-120,-120,-120,-120,-120,-120,-120,-120,-120,-120,-120,-120,-120,-120,-120', 'library')