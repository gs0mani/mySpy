from django.conf.urls import url
from rest_framework.urlpatterns import format_suffix_patterns
import views

urlpatterns = [

   url(r'^learn/$', views.LearnTrack.as_view(), name = 'learn'),
   url(r'^track/$', views.LearnTrack.as_view(), name = 'track'),

]

#urlpatterns = format_suffix_patterns(urlpatterns)
