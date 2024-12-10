class Elenco:
    def __init__(self):
        self.elenco=[]

    def add(self, value):
        self.elenco.append(value)

    def search(self, value):
        for i in range(0, len(self.elenco)):
            if self.elenco == value : return i;
        return None
    
    def delede(self, value):
        i = self.search(value)
        if(i != None) : self.elenco.pop(i)

if __name__ == '__main__' :
    print('Errore, questo file non è eseguibile')