# -*- coding: utf-8 -*-
# Generated by Django 1.11.10 on 2018-02-03 11:40
from __future__ import unicode_literals

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('myspyapi', '0007_locationdata_userhandle'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='locationdata',
            name='userhandle',
        ),
    ]
