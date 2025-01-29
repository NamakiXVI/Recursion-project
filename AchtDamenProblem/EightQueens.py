#Libraries werden importiert
import customtkinter as ctk
from PIL import Image

#die Größe des Feldes wird festgelegt
boardSize = 8

board = [[False for i in range(0, boardSize)] for i in range(0, boardSize)]

targetQueens = boardSize

currentQueens = 0

primaryColor = "#418BCA" #primärfarbe wird als Hexcode definiert

secondaryColor = "#EAF4FE" #sekundärfarbe wird als Hexcode definiert

fieldSize = 80 #Kantenlänge für ein Feld wird festgelegt

screenSize = (fieldSize + 5) * boardSize + 100 #Größe des Fensters wird basierend auf der Anzahl der Felder festgelegt

root = ctk.CTk() #Das Fenster wird initialisiert
root.configure(width=screenSize, height=screenSize) #Fenstergröße wird festgelegt

root.resizable(False, False) #Fenstergröße wird fixiert

uiFields = [[ctk.CTkLabel(master=root, width=fieldSize, height=fieldSize, text="", fg_color=primaryColor if i % 2 else "black", corner_radius=5) for i in range(0, boardSize)]
            for i in range(0, boardSize)] #Array für board wird Erstellt und initialisiert

queenImage = ctk.CTkImage(Image.open("queen.png"), size=(fieldSize - 10, fieldSize - 10)) #Bild für die Königin wird referenziert und skaliert

#Methode zum aufsetzten für das UI
def setUserInterface():
    currentColor = primaryColor #in der Variable currentColor wird die aktuelle Farbe gespeichert mit der das Feld angezeigt wird

    for x in range(0, boardSize):
        for y in range(0, boardSize):

            uiFields[x][y].place(x= (fieldSize + 5) * x + 50, y=(fieldSize + 5) * y + 50)
            uiFields[x][y].configure(fg_color=currentColor)
            if currentColor == primaryColor: #Logik um das gewünschte SchachBrettmuster zu erstellen
                currentColor = secondaryColor
            else:
                currentColor = primaryColor
        if currentColor == primaryColor:
            currentColor = secondaryColor
        else:
            currentColor = primaryColor


def solveBoard(row):
    global currentQueens
    global targetQueens #Globale Variabeln werden mit global versehen um sie in der methode zu verwenden

    if currentQueens >= targetQueens: #wenn alle Queens platziert sind wird True zurückgegeben
        return True

    for col in range(0, boardSize):
        if isSafe(row, col): #Es wird geprüft ob die Position geschlagen werden kann
            board[row][col] = True #wenn sie nicht geschlagen wird wird eine Figur dorthin gestellt
            currentQueens += 1
            if solveBoard(row + 1): #Stack wird aufgebaut
                return True
            board[row][col] = False #Figur wird wieder weggenommen wenn keine Lösung gefunden wurde
            currentQueens -= 1

    return False

#Methode zum überprüfen ob ein Feld geschlagen werden kann
def isSafe(row, col):

    #Spalte Diagonale wird geprüft
    for i in range(row):
        if board[i][col]:
            return False

    #Linke Diagonale wird geprüft
    i, j = row, col
    while i >= 0 and j >= 0:
        if board[i][j]:
            return False
        i -= 1
        j -= 1

    #Rechte Diagonale wird geprüft
    i, j = row, col
    while i >= 0 and j < len(board):
        if board[i][j]:
            return False
        i -= 1
        j += 1

    #Zeile wird nicht geprüft, da nach jedem platzieren die Zeile gewechselt wird

    return True

#main Methode
def main():
    if solveBoard(0):
        print("Solved")

    else:
        print("No solution found")

#Methode zum updaten der UI
def updateUserInterface():
    for x in range(0, boardSize):
        for y in range(0, boardSize):
            if board[x][y]:
                uiFields[x][y].configure(image=queenImage)
            else:
                uiFields[x][y].configure(image=None)


# Alle werte in "board" werden auf False gesetzt
for x in range(0, boardSize):
    for y in range(0, boardSize):
        board[x][y] = False

main() #main Methode wird ausgeführts
setUserInterface()  #Methode zum aufsetzten der UI wird aufgerufen
updateUserInterface() #UI wird geupdated
root.mainloop() #Die Mainloop wird ausgeführt um das Programm offen zu halten bis es geschlossen wird

