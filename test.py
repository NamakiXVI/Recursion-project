import customtkinter as ctk

class StyledEntry(ctk.CTkEntry):
    def __init__(self, parent, **kwargs):
        super().__init__(parent, **kwargs)
        self.configure(
            width=200,
            height=45,
            fg_color="transparent",
            text_color="white",
            font=("Arial", 15),
            corner_radius=6,
            border_width=0
        )
        self.bind("<FocusIn>", self.on_focus_in)
        self.bind("<FocusOut>", self.on_focus_out)
        
        self.label = ctk.CTkLabel(parent, text="Label", text_color="white", font=("Arial", 15))
        self.label.place(x=self.winfo_x() + 10, y=self.winfo_y() + 13)
        
        self.default_shadow()
        
    def default_shadow(self):
        self.configure(border_width=2, border_color="gray")
    
    def on_focus_in(self, event):
        self.configure(border_width=2, border_color="white")
        self.label.place(x=self.winfo_x() + 2, y=self.winfo_y() - 22)
        
    def on_focus_out(self, event):
        if not self.get():
            self.label.place(x=self.winfo_x() + 10, y=self.winfo_y() + 13)
        self.default_shadow()

# Anwendung testen
app = ctk.CTk()
app.geometry("300x200")
app.configure(bg_color="black")

entry = StyledEntry(app)
entry.place(x=50, y=50)

app.mainloop()
