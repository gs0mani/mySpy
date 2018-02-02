from rest_framework import serializers
from models import Locationdata

class LocationdataSerializer(serializers.ModelSerializer):
	
    class Meta:
        model = Locationdata
        fields = '__all__'
