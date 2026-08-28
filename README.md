Kolokvijum 1
Kreirati novi projekat pod nazivom Kolokvijum1. Odraditi sledeće zadatke:
1. Unutar MainActivity postaviti Toolbar i LinearLayout sa narandžastom pozadinom. (1.5)
2. Kreirati fragment: RecipeFragment. (1)
3. RecipeFragment sadrži RecyclerView i služi za prikaz recepta. U dnu fragmenta dodati
dugme sa nazivom "Dodaj", a iznad dugmeta ImageView sa proizvoljnom sličicom koji
je inicijalno sakriven (visibility GONE). (2)
4. Dodati meni komponentu sa stavkom Recipe. (0.5)
5. Klikom na Recipe, unutar MainActivity se prikazuje RecipeFragment. (0.5)
6. Klikom na dugme "Dodaj" otvara se forma za dodavanje novog recepta. (0.5)
7. Forma ima: naziv recepta, vreme pripreme u minutima (EditText brojčanog tipa),
Checkbox ispred koga stoji labela "Omiljeno", dugme za potvrdu i dugme za odustajanje
(1.5). Klikom na dugme za odustajanje zatvoriti formu (0.5). Klikom na potvrdu dodati
recept u RecyclerView (2.5).
8. Kreirati BroadcastReceiver. Receiver osluškuje dodavanje recepta i vodi tekući zbir
vremena pripreme svih dodatih recepata. Kada zbir pređe 120 minuta, u Toast poruci
ispisati "Predugo kuvanje!" (4)
9. Kreirati servis koji se pokreće na svaki minut (2.5) i proverava da li je dozvoljena kamera
(obavezno tražiti dozvolu) (1.5). Ako je kamera dozvoljena, prikazati sakriveni
ImageView u fragmentu. (1.5)
