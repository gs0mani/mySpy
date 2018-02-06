# -*- coding: utf-8 -*-
# Generated by Django 1.11.10 on 2018-02-03 11:13
from __future__ import unicode_literals

from django.conf import settings
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
        ('myspyapi', '0004_auto_20180203_1634'),
    ]

    operations = [
        migrations.AddField(
            model_name='locationdata',
            name='user',
            field=models.ForeignKey(default=1, on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL),
        ),
    ]