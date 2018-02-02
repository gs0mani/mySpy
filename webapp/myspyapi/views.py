# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render
from rest_framework import status
from models import Locationdata
from serializers import LocationdataSerializer
from django.http import Http404
from rest_framework.views import APIView
from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import status


# Create your views here.

# locate position ( returns coordinates given tag )
# track position (returns tag given coordinates )
# learn: add data to database
# track: still add data to database
# show user analytics
# show others location

#get room_id, post_learn_data
class LearnTrack(APIView):

    def post(self, request, format=None):
        #This is done.
        serializer = LocationdataSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def get(self, request, format = None):
    	# TODO: Apply ML here
        location = Locationdata.objects.all()
        serializer = LocationdataSerializer(location, many=True)
        return Response(serializer.data)

