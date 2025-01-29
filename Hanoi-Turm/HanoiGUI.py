import customtkinter as ctk
import time
from threading import Thread

class HanoiGUI(ctk.CTk):
    def __init__(self):
        super().__init__()
        ctk.set_appearance_mode("dark")
        ctk.set_default_color_theme("blue")
        self.title("Turm von Hanoi")
        self.geometry("1200x800")
        self.configure(bg="#F5F5F7")
        self.create_home_screen()
    
    def create_home_screen(self):
        self.clear_window()
        title = ctk.CTkLabel(self, text="Turm von Hanoi", font=("San Francisco", 48, "bold"), text_color="white")
        title.pack(pady=50)
        
        button_frame = ctk.CTkFrame(self, fg_color="transparent")
        button_frame.pack(pady=50)
        
        start_button = ctk.CTkButton(button_frame, text="Start", command=self.show_main_frame, corner_radius=10, font=("San Francisco", 18))
        exit_button = ctk.CTkButton(button_frame, text="Beenden", command=self.quit, corner_radius=10, font=("San Francisco", 18))
        
        start_button.pack(side="left", padx=20, ipadx=20, ipady=10)
        exit_button.pack(side="left", padx=20, ipadx=20, ipady=10)
    
    def show_main_frame(self):
        self.clear_window()
        
        self.towers = {"A": [], "B": [], "C": []}
        
        control_frame = ctk.CTkFrame(self, fg_color="transparent")
        control_frame.pack(pady=20)
        
        ctk.CTkLabel(control_frame, text="Anzahl der Scheiben:", font=("San Francisco", 16)).grid(row=0, column=0, padx=10, pady=10)
        self.disk_input = ctk.CTkEntry(control_frame, font=("San Francisco", 16))
        self.disk_input.grid(row=0, column=1, padx=10, pady=10)
        
        ctk.CTkLabel(control_frame, text="Verzögerung (ms):", font=("San Francisco", 16)).grid(row=1, column=0, padx=10, pady=10)
        self.delay_input = ctk.CTkEntry(control_frame, font=("San Francisco", 16))
        self.delay_input.grid(row=1, column=1, padx=10, pady=10)
        
        button_frame = ctk.CTkFrame(self, fg_color="transparent")
        button_frame.pack(pady=20)
        
        solve_button = ctk.CTkButton(button_frame, text="Sortierung starten", command=self.start_hanoi, corner_radius=10, font=("San Francisco", 18))
        back_button = ctk.CTkButton(button_frame, text="Zurück zum Start", command=self.create_home_screen, corner_radius=10, font=("San Francisco", 18))
        
        solve_button.pack(side="left", padx=20, ipadx=20, ipady=10)
        back_button.pack(side="left", padx=20, ipadx=20, ipady=10)
        
        self.canvas = ctk.CTkCanvas(self, width=1000, height=500, bg="#E5E5EA")
        self.canvas.pack(pady=20)
    
    def start_hanoi(self):
        try:
            n = int(self.disk_input.get())
            delay = int(self.delay_input.get()) / 1000
        except ValueError:
            return
        
        self.towers["A"] = list(range(n, 0, -1))
        self.towers["B"] = []
        self.towers["C"] = []
        self.update_canvas()
        
        thread = Thread(target=self.solve_hanoi, args=(n, "A", "C", "B", delay))
        thread.start()
    
    def solve_hanoi(self, n, from_tower, to_tower, aux_tower, delay):
        if n == 1:
            self.move_disk(from_tower, to_tower, delay)
            return
        self.solve_hanoi(n - 1, from_tower, aux_tower, to_tower, delay)
        self.move_disk(from_tower, to_tower, delay)
        self.solve_hanoi(n - 1, aux_tower, to_tower, from_tower, delay)
    
    def move_disk(self, from_tower, to_tower, delay):
        if self.towers[from_tower]:
            self.towers[to_tower].append(self.towers[from_tower].pop())
            self.update_canvas()
            time.sleep(delay)
    
    def update_canvas(self):
        self.canvas.delete("all")
        positions = {"A": 250, "B": 500, "C": 750}
        for tower, x in positions.items():
            self.canvas.create_rectangle(x - 10, 100, x + 10, 400, fill="#1D1D1F")
            for i, disk in enumerate(self.towers[tower]):
                width = disk * 20
                y = 380 - i * 20
                self.canvas.create_rectangle(x - width//2, y, x + width//2, y + 20, fill="#007AFF", outline="black")
    
    def clear_window(self):
        for widget in self.winfo_children():
            widget.destroy()

if __name__ == "__main__":
    app = HanoiGUI()
    app.mainloop()
