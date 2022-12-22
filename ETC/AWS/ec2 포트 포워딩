# 포트포워딩

8080포트에 있는 서비스에 접속하려면 example.com:8080 포트를 붙여야 되는데 포트포워딩을 하면
example.com 으로 들어오는 요청을 example.com:8080 으로 포워딩할 수 있음.

ec2 인스턴스에 관리자 권한으로 접속
> sudo su

80번 포트로 들어오는 요청을 8080으로 포워딩
> iptables -A PREROUTING -t nat -i eth0 -p tcp --dport 80 -j REDIRECT --to-port 8080
