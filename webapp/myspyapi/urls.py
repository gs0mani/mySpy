from django.conf.urls import url, include
from django.contrib import admin
from rest_framework.urlpatterns import format_suffix_patterns
import views
from django.contrib.auth import views as auth_views

urlpatterns = [

   url(r'^learn/$', views.LearnTrack.as_view(), name = 'learn'),
   url(r'^track/$', views.LearnTrack.as_view(), name = 'track'),
   url(r'^login/$', auth_views.login),
   url(r'^logout/$', auth_views.logout),
   url(r'^$', views.home),
   url(r'^register/', views.register),
]

#urlpatterns = format_suffix_patterns(urlpatterns)
