# -*- coding: utf-8 -*-
# Generated by Django 1.11.10 on 2018-02-03 11:04
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('myspyapi', '0003_auto_20180203_1620'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='locationdata',
            name='user',
        ),
        migrations.AddField(
            model_name='locationdata',
            name='userhandle',
            field=models.CharField(default='admin', max_length=255),
        ),
    ]
