# -*- coding: utf-8 -*-
from __future__ import unicode_literals
import ast

from django.shortcuts import render
from rest_framework import status

from models import Locationdata

from serializers import LocationdataSerializer, LocationdataSerializer2
from django.http import Http404
from rest_framework.views import APIView
from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import status
import urlparse

from django.contrib.auth import views as auth_views
from django.contrib.auth.middleware import AuthenticationMiddleware
from django.views.decorators.csrf import csrf_exempt
from django.shortcuts import render
from django.contrib.auth.models import User, Group, Permission
from django.contrib.auth import authenticate, login
from django.http import HttpResponseRedirect
from django import forms
from .forms import UserRegistrationForm

import pickle, os

from rest_framework.test import APIRequestFactory
from django.contrib.auth import views as auth_views


import mlearn
@csrf_exempt
def home(request):
	return render(request, 'myspyapi/index.html')

def login(request):
	if request == 'POST':
		return Response(auth_views.login(request))
	else:
		form = UserLoginForm()

	return render(request, 'registration/login.html', {'form' : form})

@csrf_exempt
def register(request):
	if request.method == 'POST':
		print request.POST
		form = UserRegistrationForm(request.POST)
		if form.is_valid():
			userObj = form.cleaned_data
			username = userObj['username']
			email =  userObj['email']
			password =  userObj['password']
			if not (User.objects.filter(username=username).exists() or User.objects.filter(email=email).exists()):
				User.objects.create_user(username, email, password)
				client_group = Group.objects.get(name='Client_users')
				user = User.objects.get(username=username)
				user.groups.add(client_group)
				user = authenticate(username = username, password = password)
				login(user)
				return HttpResponseRedirect('/')
			else:
				raise forms.ValidationError('Looks like a username with that email or password already exists')

	else:
		form = UserRegistrationForm()

	return render(request, 'myspyapi/register.html', {'form' : form})


# locate position ( returns coordinates given tag )
# track position (returns tag given coordinates )
# learn: add data to database
# track: still add data to database
# show user analytics
# show others location

#get room_id, post_learn_data

def LearnTrackHelper(request):
	#url = request.url
	try:
		input_wifi = ast.literal_eval(request.query_params['wifi'])
	except:
		return False

	input_building = request.query_params['building']

	dir = os.path.dirname(__file__)
	filename = os.path.join(dir, 'wifi_hash.pkl')
	
	pkl_file = open(filename, 'rb')
	hash_table = pickle.load(pkl_file)
	pkl_file.close()
	
	if input_building not in hash_table:
		hash_table[input_building] = {}
	
	return_list = ["-120"]*25 # Considering max 25 wifis in a building

	for dic_wifi in input_wifi:
		value = dic_wifi['rssi']
		macid = dic_wifi['mac']
		l = len(hash_table[input_building])
		if macid not in hash_table[input_building]:
			hash_table[input_building][macid] = l
		return_list[hash_table[input_building][macid]] = str(value)
	
	output = open(filename, 'wb')
	pickle.dump(hash_table, output)
	output.close()
	return ','.join(return_list)

#get room_id, post_learn_data
class LearnTrack(APIView):

	#learn
	def post(self, request, format=None):
		#This is done. Add location
		#print request.query_params
		#print request.user
		integer_list = LearnTrackHelper(request)
		if integer_list == False:
			return Response({'server_response': 'Error in specifying wifi attribute'}, status=status.HTTP_400_BAD_REQUEST)
		mutable = request.query_params._mutable
		request.query_params._mutable = True
		#print request.query_params._mutable
		request.query_params['wifi'] = integer_list
		request.query_params._mutable = mutable
		serializer = LocationdataSerializer2(data=request.query_params)
		print request.query_params
		if serializer.is_valid():
			serializer.save()
			#print serializer.data
			return Response({"Success": "Your location has been added to our databse"}, status=status.HTTP_201_CREATED)
		return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

	#track
	def get(self, request, format = None):
		integer_list = LearnTrackHelper(request)
		if integer_list == False:
			return Response({'server_response': 'Error in specifying wifi attribute'}, status=status.HTTP_400_BAD_REQUEST)
		if not request.GET._mutable:
			request.GET._mutable = True
		request.query_params['wifi'] = integer_list
		request.GET._mutable = False
		serializer = LocationdataSerializer(data=request.query_params)
		
		if serializer.is_valid():
		# TODO: Apply ML here
			building_name = request.query_params['building']
			rf_output = mlearn.random_forest(integer_list, building_name)
			print serializer.data
		#location = Locationdata.objects.all()
		#serializer = LocationdataSerializer(location, many=True)
			return Response(rf_output)
		return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

