#coding=utf-8
import re
import os
import urllib.parse
import shutil

#duqu shuju 

class seqNode:
    def __init__(self):
        self.ID = ''
        self.name= ""
        self.ch_name = ""
    def show(self):
        print(self.ID+" "+self.ch_name+' '+self.name+" ")
        print('\n')
    def geturl(self):
        return "https://leetcode-cn.com/problems/" + self.name 

data ={}

def readData(data):
    l = []
    with open('abc.txt', 'r',encoding='UTF-8') as f:
        l =f.readlines()
        for i in l:
            i.rstrip()
            ll = i.split('|')
            node = seqNode()
            node.ID = ll[0]
            node.name = ll[1]
            node.ch_name = ll[2]
            #node.show()
            data[node.ID.strip()] =  node

def getdirname(idx):
    idx =int(idx)
    idx = int(idx/100)
    pathname = str(idx*100)+"~"+str((idx+1)*100)
    return pathname


readData(data)
cwd = os.getcwd()



getName =input("Plz Input the Question idx\n")
getName = getName.strip()
title = data[getName].ch_name.replace(' ','_').rstrip()

idxDirName = getdirname(getName)
print(idxDirName)
dirname = os.path.join(cwd,idxDirName)
newpath = '['+ getName.zfill(4) + ']' +title
dirname = os.path.join(dirname,newpath)

filename = os.path.join(dirname,title+'.md')
if os.path.isdir(dirname):
   print("the dir has excited")
else :
    print("Creating the dir as follow:")
    os.mkdir(dirname)
    F =open(filename,'x',encoding='UTF-8')
    F.write("![](https://github.com/Sologala/SomeThings/blob/master/face.jpg?raw=true)\n\n")
    F.write("/*\n")
    F.write("    Sologala   @github    https://github.com/Sologala/LeetCode.git\n\n")
    print(data[getName].ch_name +'     |    ' +data[getName].name)
    F.write("   "+"["+getName+']'+data[getName].ch_name +'     |    ' +data[getName].name+'\n')
    F.write("\n*/\n")
    F.write("\n\n\n##**Solution** \n\n### **ac_code**\n```c\n\n```")
    F.close()
os.system("typora "+filename)
