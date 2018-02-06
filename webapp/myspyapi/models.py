# -*- coding: utf-8 -*-
from __future__ import unicode_literals
from django.core.validators import int_list_validator
from django.db import models
from django.contrib.auth.models import User

# Create your models here.
class Locationdata(models.Model):
    building  = models.CharField(max_length=255)
    user = models.ForeignKey(User, default = 1)
    room = models.CharField(max_length=255)
    wifi = models.CharField(validators=[int_list_validator(
        sep=',', message="Integer Comma Error", allow_negative=True)], max_length=1000)
    date_created = models.DateTimeField(auto_now_add=True)
    userhandle = models.CharField(max_length=255, default='admin')
    #latitude
    #longitude
    class Meta:
        db_table = 'Locationdata'
    #add if geolocation api works on the mobile device
    def __str__(self):
        return self.building

