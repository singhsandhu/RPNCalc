# -*- mode: ruby -*-
# vi: set ft=ruby :

NODE = "rpn-calculator"
VAGRANTFILE_API_VERSION = "2"
NODE_IP = '11.0.0.100'

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
    config.vm.define NODE, primary: true do |service|
    service.vm.box_url = "https://vagrantcloud.com/ubuntu/trusty64"
    service.vm.box = "ubuntu/trusty64"

    service.vm.hostname = NODE
    service.vm.network :private_network, ip: NODE_IP

    service.vm.synced_folder ".", "/vagrant"

    service.vm.provision "ansible_local" do |ansible|
       ansible.playbook = "playbook.yml"
    end

    service.vm.provider :virtualbox do |vb|
      vb.memory = 2048
    end
  end

end
