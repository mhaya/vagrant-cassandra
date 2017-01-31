VAGRANTFILE_API_VERSION = "2"
Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|

  config.vm.boot_timeout = 500
  config.vm.box = "centos7"
  #config.vm.box_url = "https://googledrive.com/host/0B2JacpSnObRwRDk2UVZONC1HTUE/centos7.box"
  #config.vm.box = "centos"
  #config.vm.box_url = "https://googledrive.com/host/0B2JacpSnObRwOFVnbFJkSDNvaEE"
  config.ssh.insert_key = false


  config.vm.define :node1 do | node1 |
    node1.vm.hostname = "node1"
    node1.vm.network :private_network, ip: "192.168.12.10"
    node1.vm.network :forwarded_port, id: "ssh", guest: 22, host: 2212
  end

  config.vm.define :node2 do | node2 |
     node2.vm.hostname = "node2"
     node2.vm.network :private_network, ip: "192.168.12.11"
     node2.vm.network :forwarded_port, id: "ssh", guest: 22, host: 2210
  end

  config.vm.define :node3 do | node3 |
     node3.vm.hostname = "node3"
     node3.vm.network :private_network, ip: "192.168.12.12"
     node3.vm.network :forwarded_port, id: "ssh", guest: 22, host: 2211
  end

#  config.vm.provision "ansible" do |ansible|
    #ansible.verbose = "vvv"
#    ansible.ask_sudo_pass = false
#    ansible.playbook = "./site.yml"
#    ansible.limit = "all"
#      ansible.groups = {
#      "seednode" => ["node1"],
#      "node" => ["node2","node3"]
#    }
#  end
end
