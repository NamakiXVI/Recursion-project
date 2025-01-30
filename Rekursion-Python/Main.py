import customtkinter as ctk  # importiert das customtkinter modul für das gui
from customtkinter import *  # importiert alle klassen und funktionen aus customtkinter
import time  # importiert das time modul für zeitbezogene operationen
from threading import Thread  # importiert die thread klasse für nebenläufige prozesse
from PIL import Image  # importiert das image modul aus der pillow bibliothek für bildverarbeitung

class Main(ctk.CTk):  # definiert die hauptklasse der anwendung die von ctk.CTk erbt
    def __init__(self):  # konstruktor der hauptklasse
        super().__init__()  # ruft den konstruktor der elternklasse auf
        ctk.set_appearance_mode("dark")  # setzt das farbschema der anwendung auf dunkel
        ctk.set_default_color_theme("blue")  # setzt das standardfarbthema auf blau
        self.title("Rekursion Projekte")  # setzt den titel des fensters
        self.geometry("1200x800")  # setzt die größe des fensters auf 1200x800 pixel
        self.configure(bg="#F5F5F7")  # setzt die hintergrundfarbe des fensters
        self.create_home_screen()  # ruft die methode auf um den startbildschirm zu erstellen
    
    def create_home_screen(self):  # methode zum erstellen des startbildschirms
        self.clear_window()  # löscht alle widgets im fenster
        title = ctk.CTkLabel(self, text="Algorithmus Visualisierung", font=("San Francisco", 48, "bold"), text_color="white")  # erstellt ein label mit dem titel
        title.pack(pady=50)  # platziert das label im fenster mit abstand nach oben und unten
        
        button_frame = ctk.CTkFrame(self, fg_color="transparent")  # erstellt einen transparenten rahmen für die buttons
        button_frame.pack(pady=50, padx=20, anchor="w")  # platziert den rahmen im fenster
        
        hanoi_button = ctk.CTkButton(button_frame, text="Turm von Hanoi", command=self.Hanoi_frame, corner_radius=100, font=ctk.CTkFont("San Francisco", 28, underline=True, weight="bold"), bg_color="transparent", fg_color="transparent")  # erstellt einen button für das turm von hanoi problem
        eight_queen_button = ctk.CTkButton(button_frame, text="8 Damen Problem", command=self.EightQueen_frame, corner_radius=100, font=ctk.CTkFont("San Francisco", 28, underline=True, weight="bold"), bg_color="transparent", fg_color="transparent")  # erstellt einen button für das 8 damen problem
        exit_button = ctk.CTkButton(button_frame, text="Beenden", command=self.quit, corner_radius=100, font=ctk.CTkFont("San Francisco", 28, underline=True, weight="bold"), bg_color="transparent", fg_color="transparent")  # erstellt einen button zum beenden der anwendung
        
        hanoi_button.pack(fill="x", anchor="w", pady=10, ipadx=0, ipady=10)  # platziert den hanoi button im rahmen
        eight_queen_button.pack(fill="x", anchor="w", pady=10, ipadx=0, ipady=10)  # platziert den 8 damen button im rahmen
        exit_button.pack(fill="x", anchor="w", pady=10, ipadx=0, ipady=10)  # platziert den beenden button im rahmen

    def EightQueen_frame(self):  # methode zum erstellen des 8 damen problem bildschirms
        self.clear_window()  # löscht alle widgets im fenster

        self.board_size = 8  # setzt die größe des schachbretts auf 8x8
        self.board = [[False for _ in range(self.board_size)] for _ in range(self.board_size)]  # initialisiert das schachbrett als 2d-liste
        self.current_queens = 0  # setzt die anzahl der platzierten damen auf 0
        self.target_queens = self.board_size  # setzt die zielanzahl der damen auf 8
        
        self.primary_color = "#418BCA"  # definiert die primäre farbe für das schachbrett
        self.secondary_color = "#EAF4FE"  # definiert die sekundäre farbe für das schachbrett
        self.field_size = 80  # setzt die größe eines feldes auf 80x80 pixel
        self.screen_size = (self.field_size + 5) * self.board_size + 100  # berechnet die größe des fensters basierend auf der feldgröße
        
        self.title("8 Damen Problem")  # setzt den titel des fensters
        self.geometry(f"{self.screen_size}x{self.screen_size * 1.1}")  # setzt die größe des fensters
        self.resizable(True, True)  # erlaubt das ändern der fenstergröße
        
        self.ui_fields = [[ctk.CTkLabel(self, width=self.field_size, height=self.field_size, text="", 
                                        fg_color=self.primary_color if (i + j) % 2 == 0 else self.secondary_color, corner_radius=5) 
                           for j in range(self.board_size)] for i in range(self.board_size)]  # erstellt die felder des schachbretts als labels
        
        self.queen_image = ctk.CTkImage(Image.open("queen.png"), size=(self.field_size - 10, self.field_size - 10))  # lädt das bild der dame

        button_frame = ctk.CTkFrame(self, fg_color="transparent")  # erstellt einen transparenten rahmen für den zurück button
        button_frame.pack(side="bottom", pady=10)  # platziert den rahmen im fenster
        back_button = ctk.CTkButton(button_frame, text="Zurück zum Start", command=self.create_home_screen, corner_radius=10, font=("San Francisco", 18))  # erstellt den zurück button
        back_button.pack(side="right", fill="x", ipadx=20, ipady=10)  # platziert den zurück button im rahmen
        
        self.setup_ui()  # ruft die methode auf um das schachbrett im fenster anzuzeigen
        self.solve_board(0)  # löst das 8 damen problem
        self.update_ui()  # aktualisiert die oberfläche mit der lösung
    
    def setup_ui(self):  # methode zum platzieren der felder im fenster
        for x in range(self.board_size):  # iteriert über die reihen des schachbretts
            for y in range(self.board_size):  # iteriert über die spalten des schachbretts
                self.ui_fields[x][y].place(x=(self.field_size + 5) * y + 50, y=(self.field_size + 5) * x + 50)  # platziert das feld an der entsprechenden position
    
    def solve_board(self, row):  # rekursive methode zum lösen des 8 damen problems
        if self.current_queens >= self.target_queens:  # überprüft ob alle damen platziert wurden
            return True
        
        for col in range(self.board_size):  # iteriert über die spalten der aktuellen reihe
            if self.is_safe(row, col):  # überprüft ob das feld sicher ist
                self.board[row][col] = True  # platziert eine dame auf dem feld
                self.current_queens += 1  # erhöht die anzahl der platzierten damen
                if self.solve_board(row + 1):  # ruft die methode rekursiv für die nächste reihe auf
                    return True
                self.board[row][col] = False  # entfernt die dame vom feld
                self.current_queens -= 1  # verringert die anzahl der platzierten damen
        
        return False
    
    def is_safe(self, row, col):  # methode zur überprüfung ob ein feld sicher ist
        for i in range(row):  # iteriert über die vorherigen reihen
            if self.board[i][col]:  # überprüft ob eine dame in der gleichen spalte ist
                return False
        
        i, j = row, col  # initialisiert die variablen für die diagonale überprüfung
        while i >= 0 and j >= 0:  # überprüft die diagonale nach links oben
            if self.board[i][j]:  # überprüft ob eine dame auf dem feld ist
                return False
            i -= 1  # bewegt sich eine reihe nach oben
            j -= 1  # bewegt sich eine spalte nach links
        
        i, j = row, col  # initialisiert die variablen für die diagonale überprüfung
        while i >= 0 and j < self.board_size:  # überprüft die diagonale nach rechts oben
            if self.board[i][j]:  # überprüft ob eine dame auf dem feld ist
                return False
            i -= 1  # bewegt sich eine reihe nach oben
            j += 1  # bewegt sich eine spalte nach rechts
        
        return True  # gibt true zurück wenn das feld sicher ist
    
    def update_ui(self):  # methode zur aktualisierung der oberfläche
        for x in range(self.board_size):  # iteriert über die reihen des schachbretts
            for y in range(self.board_size):  # iteriert über die spalten des schachbretts
                if self.board[x][y]:  # überprüft ob eine dame auf dem feld ist
                    self.ui_fields[x][y].configure(image=self.queen_image)  # setzt das bild der dame auf dem feld
                else:
                    self.ui_fields[x][y].configure(image=None)  # entfernt das bild vom feld

    def Hanoi_frame(self):  # methode zum erstellen des turm von hanoi bildschirms
        self.clear_window()  # löscht alle widgets im fenster
        
        self.towers = {"A": [], "B": [], "C": []}  # initialisiert die türme als leere listen
        self.geometry("1200x800")  # setzt die größe des fensters auf 1200x800 pixel
        control_frame = ctk.CTkFrame(self, fg_color="transparent")  # erstellt einen transparenten rahmen für die steuerelemente
        control_frame.pack(pady=20)  # platziert den rahmen im fenster
        
        ctk.CTkLabel(control_frame, text="Anzahl der Scheiben:", font=("San Francisco", 16)).grid(row=0, column=0, padx=10, pady=10)  # erstellt ein label für die scheibenanzahl
        self.disk_input = ctk.CTkEntry(control_frame, font=("San Francisco", 16))  # erstellt ein eingabefeld für die scheibenanzahl
        self.disk_input.grid(row=0, column=1, padx=10, pady=10)  # platziert das eingabefeld im rahmen
        
        ctk.CTkLabel(control_frame, text="Verzögerung (ms):", font=("San Francisco", 16)).grid(row=1, column=0, padx=10, pady=10)  # erstellt ein label für die verzögerung
        self.delay_input = ctk.CTkEntry(control_frame, font=("San Francisco", 16))  # erstellt ein eingabefeld für die verzögerung
        self.delay_input.grid(row=1, column=1, padx=10, pady=10)  # platziert das eingabefeld im rahmen
        
        button_frame = ctk.CTkFrame(self, fg_color="transparent")  # erstellt einen transparenten rahmen für die buttons
        button_frame.pack(pady=20)  # platziert den rahmen im fenster
        
        solve_button = ctk.CTkButton(button_frame, text="Sortierung starten", command=self.start_hanoi, corner_radius=10, font=("San Francisco", 18))  # erstellt einen button zum starten der sortierung
        back_button = ctk.CTkButton(button_frame, text="Zurück zum Start", command=self.create_home_screen, corner_radius=10, font=("San Francisco", 18))  # erstellt einen button zum zurückkehren zum startbildschirm
        
        solve_button.pack(side="left", padx=20, ipadx=20, ipady=10)  # platziert den start button im rahmen
        back_button.pack(side="left", padx=20, ipadx=20, ipady=10)  # platziert den zurück button im rahmen
        
        self.canvas = ctk.CTkCanvas(self, width=1000, height=500, bg="#E5E5EA")  # erstellt ein canvas für die visualisierung der türme
        self.canvas.pack(pady=20)  # platziert das canvas im fenster
    
    def start_hanoi(self):  # methode zum starten der turm von hanoi lösung
        try:
            n = int(self.disk_input.get())  # liest die anzahl der scheiben aus dem eingabefeld
            delay = int(self.delay_input.get()) / 1000  # liest die verzögerung aus dem eingabefeld und konvertiert sie in sekunden
        except ValueError:  # fängt fehler bei der eingabe ab
            return
        
        self.towers["A"] = list(range(n, 0, -1))  # initialisiert den turm a mit den scheiben
        self.towers["B"] = []  # initialisiert den turm b als leer
        self.towers["C"] = []  # initialisiert den turm c als leer
        self.update_canvas()  # aktualisiert das canvas mit den initialen türmen
        
        thread = Thread(target=self.solve_hanoi, args=(n, "A", "C", "B", delay))  # erstellt einen thread für die lösung des turm von hanoi problems
        thread.start()  # startet den thread
    
    def solve_hanoi(self, n, from_tower, to_tower, aux_tower, delay):  # rekursive methode zum lösen des turm von hanoi problems
        if n == 1:  # überprüft ob nur eine scheibe zu bewegen ist
            self.move_disk(from_tower, to_tower, delay)  # bewegt die scheibe
            return
        self.solve_hanoi(n - 1, from_tower, aux_tower, to_tower, delay)  # bewegt n-1 scheiben vom startturm zum hilfsturm
        self.move_disk(from_tower, to_tower, delay)  # bewegt die verbleibende scheibe zum zielturm
        self.solve_hanoi(n - 1, aux_tower, to_tower, from_tower, delay)  # bewegt die n-1 scheiben vom hilfsturm zum zielturm
    
    def move_disk(self, from_tower, to_tower, delay):  # methode zum bewegen einer scheibe
        if self.towers[from_tower]:  # überprüft ob der startturm scheiben enthält
            self.towers[to_tower].append(self.towers[from_tower].pop())  # bewegt die oberste scheibe vom startturm zum zielturm
            self.update_canvas()  # aktualisiert das canvas
            time.sleep(delay)  # wartet die angegebene verzögerung
    
    def update_canvas(self):  # methode zur aktualisierung des canvas
        self.canvas.delete("all")  # löscht alle elemente auf dem canvas
        positions = {"A": 250, "B": 500, "C": 750}  # definiert die positionen der türme
        for tower, x in positions.items():  # iteriert über die türme
            self.canvas.create_rectangle(x - 10, 100, x + 10, 400, fill="#1D1D1F")  # zeichnet den turm
            for i, disk in enumerate(self.towers[tower]):  # iteriert über die scheiben des turms
                width = disk * 20  # berechnet die breite der scheibe basierend auf ihrer größe
                y = 380 - i * 20  # berechnet die y-position der scheibe
                self.canvas.create_rectangle(x - width//2, y, x + width//2, y + 20, fill="#007AFF", outline="black")  # zeichnet die scheibe
    
    def clear_window(self):  # methode zum löschen aller widgets im fenster
        for widget in self.winfo_children():  # iteriert über alle widgets im fenster
            widget.destroy()  # zerstört das widget

if __name__ == "__main__":  # überprüft ob das skript direkt ausgeführt wird
    app = Main()  # erstellt eine instanz der hauptklasse
    app.mainloop()  # startet die hauptschleife der anwendung