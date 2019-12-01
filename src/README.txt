 _      _____  ___  _____ _   _ _____   ___________  ______ _____  _____ 
| |    |  ___|/ _ \|  __ \ | | |  ___| |  _  |  ___| | ___ \  _  ||  _  |
| |    | |__ / /_\ \ |  \/ | | | |__   | | | | |_    | |_/ / | | || | | |
| |    |  __||  _  | | __| | | |  __|  | | | |  _|   |  __/| | | || | | |
| |____| |___| | | | |_\ \ |_| | |___  \ \_/ / |     | |   \ \_/ /\ \_/ /
\_____/\____/\_| |_/\____/\___/\____/   \___/\_|     \_|    \___/  \___/ 


Deci logica jocului.
	Pentru citirea din fisier am reutilizat codul din scheletul Primei Teme, clasele GameInput si GameInputLoader;
	Am citit parametrii matricei si tipul de land , dupa care cu un LandFactory le-am creat.
	Am citit playerii si pozitiile lor in matrice, si mi-am creat un vector de player folosind la fel un HeroFactory.

Duelurile in sine.
	N runde dureaza un meci, la inceput de runda verific DoT aplicat , pentru a evita miscari si atacuri,
	daca cineva moare de la DoT.
	Dupa care daca amandoi eroi sunt in viata , se ataca intre ei.
	Dupa fiecare runda de atacuri verificam daca cineva a omorat inamicul si ii atribui experienta cuvenita.
	La final scriem in fisier de iesire rezultatul luptelor.

Despre skilluri.
	In principiu , am aplicat formulele din enunt verificand de fiecare data daca am un bonus de teren
	sau un bonus pentru rasa eroului pe care il atac.Aplic damage-ul direct la folosirea unui skill.
	Verific daca viata acestuia dupa skill este egala cu 0 si ii setez campul de alive pe false pentru a sti mai
	departe.
	Pentru campioni care aplica DoT(Pyro,Rogue) sau il suprascriu (Knight) am 2 campuri separate 
	pentru DoT si runde pe cat se aplica pe care le setez dupa skilluri.
	Am si un camp pentru damage reflectat de care am nevoie in Wizard .
	