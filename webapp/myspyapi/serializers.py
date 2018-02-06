from rest_framework import serializers
from models import Locationdata
from django.contrib.auth.models import User


class LocationdataSerializer(serializers.ModelSerializer):
    class Meta:
        model = Locationdata
        exclude = ('room', )


class LocationdataSerializer2(serializers.ModelSerializer):
    # user = serializers.SlugRelatedField(
    #     many=True,
    #     read_only=True,
    #     slug_field='user'
    #  )
    class Meta:
        model = Locationdata
        fields = '__all__'



