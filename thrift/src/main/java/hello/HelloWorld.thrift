namespace java thrift.hello

service HelloWorld {
    string getMessage(1:string id, 2:byte type)
}