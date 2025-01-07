class Elenco:
    def __init__(self):
        self.elenco=[]

    def add(self, value):
        self.elenco.append(value)

    def search(self, value):
        for i in range(0, len(self.elenco)):
            if self.elenco[i] == value : return i
        return None
    
    def delede(self, value):
        i = self.search(value)
        if(i != None) : self.elenco.pop(i)
    
    def __str__(self):
        s = ''
        for i in range(0, len(self.elenco)):
            s = s + self.elenco[i].__str__() + '\n'
        return s

if __name__ == '__main__' :
    print('Errore, questo file non è eseguibile')