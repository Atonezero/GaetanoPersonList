from datetime import date
import datetime

class Persona:
    def __init__(self):
        self.nome = ''
        self.DoB = ''

    #Override
    def __eq__(self, value):
        if (value == None) : return False
        if not isinstance(value, Persona): return False
        return self.__str__() == self.__str__()
    
    #Override (toString())
    def __str__(self):
        s='[' + self.nome + ', ' + self.DoB + ']'
        return s
    
    def getEta(self):
        d = datetime.datetime.strptime(self.DoB, "%d%m%Y").date()
        today = date.today()

        return today.year - d.year - ((today.month, today.day) < (d.month, d.day))
    
if __name__ == '__main__':
    print('Errore, questo file non è eseguibile')    