# -*- coding: utf-8 -*-
# Generated by Django 1.11.10 on 2018-02-03 12:26
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('myspyapi', '0008_remove_locationdata_userhandle'),
    ]

    operations = [
        migrations.AddField(
            model_name='locationdata',
            name='userhandle',
            field=models.CharField(default='admin', max_length=255),
        ),
    ]
