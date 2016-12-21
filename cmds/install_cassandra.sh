#!/bin/bash
sudo yum install -y firewalld
sudo systemctl start firewalld
sudo yum install -y jna
sudo cat << EOS > /etc/yum.repos.d/datastax.repo
[datastax]
name = DataStax Repo for Apache Cassandra
baseurl = http://rpm.datastax.com/community
enabled = 1
gpgcheck = 0
EOS
sudo yum install -y cassandra30 cassandra30-tools

sudo sed -i "s/NM_CONTROLLED=no/NM_CONTROLLED=yes/g" /etc/sysconfig/network-scripts/ifcfg-enp0s8
sudo systemctl restart NetworkManager



sudo firewall-cmd --add-port=7000/tcp --zone=public --permanent
sudo firewall-cmd --add-port=7001/tcp --zone=public --permanent
sudo firewall-cmd --add-port=7199/tcp --zone=public --permanent
sudo firewall-cmd --add-port=9042/tcp --zone=public --permanent
sudo firewall-cmd --add-port=9160/tcp --zone=public --permanent
sudo firewall-cmd --add-port=9142/tcp --zone=public --permanent
sudo firewall-cmd --reload
