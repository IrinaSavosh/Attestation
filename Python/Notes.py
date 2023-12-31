# Реализовать консольное приложение заметки, с !!!сохранением, !!!чтением,
# !!!добавлением, !!!редактированием и !!!удалением заметок. Заметка должна
# содержать идентификатор, заголовок, тело заметки и дату/время создания или
# последнего изменения заметки. Сохранение заметок необходимо сделать в
# формате json или csv формат (разделение полей рекомендуется делать через
# точку с запятой). Реализацию пользовательского интерфейса студент может
# делать как ему удобнее, можно делать как параметры запуска программы
# (команда, данные), можно делать как запрос команды с консоли и
# последующим вводом данных, как-то ещё, на усмотрение студента.Например:
# python notes.py add --title "новая заметка" –msg "тело новой заметки"
# Или так:
# python note.py
# Введите команду: add
# Введите заголовок заметки: новая заметка
# Введите тело заметки: тело новой заметки
# Заметка успешно сохранена
# Введите команду:
#  При чтении списка заметок реализовать фильтрацию по дате.
from datetime import datetime
import json

notes = []
def load():
   global notes
   with open("Notes.json","r",encoding="utf-8") as fb: 
      notes = json.load(fb)
   print("Телефонный справочник загружен из файла")

# 2
def save():   
   with open("Notes.json","w",encoding="utf-8") as fb:
        json.dumps(notes, sort_keys= 'Дата/время создания/изменения:') 
        fb.write(json.dumps(notes, ensure_ascii=False))
        print("Список заметок сохранен")
# 4
def open_note():
   print ('Заметку под каким номером Вы хотите открыть:')
   show_all_notes()
   number = int(input('''
   Введите номер: \n'''))
   print(f'№ {number}')
   for k, v in notes[number-1].items():
      print(k, v)



# 5
def red():
   print ('Заметку под каким номером Вы хотите изменить:')
   show_all_notes()
   number = int(input('''
   Введите номер: \n'''))
   option = input('''                 
   Вы хотите:
   1. Редактировать заголовок.
   2. Редактировать текст заметки.\n''')
   if option == "1":
      k= "Название:"
      notes[number-1][k] = input(str('''
   Введите новое название: ''')) 
   if option == "2":
      k= "содержание:"
      notes[number-1][k] = input(str('''
      Введите новый текст: ''')) 
   notes.insert(0, notes.pop(number-1))
   print(f'"Заметка под номером {number} отредактирована"')


#6
def delite():
   print ('Заметку под каким номером Вы хотите изменить:')
   show_all_notes()
   number = int(input('''
   Введите номер: \n'''))
   notes.pop(number-1)
   print(f'"Заметка под номером {number} удалена"')

# 3
def add():
   print('''
   Для того, чтобы добавить заметку, следуйте инструкциям ниже.\n''')
   note_title = input("Введите название заметки: ")
   text = input("Введите текст заметки: ")
   current_date = datetime.now()
   notes.insert(0, {
   "Название:": f"{note_title}",
   "содержание:": f"{text}",
   "Время создания/изменения:": f"{current_date}"
   })
   print("Абонент успешно добавлен в телефонный справочник!\n")

# 1
def show_all_notes():
   if notes == []:
         print("Заметок нет")
   else:
      i=0
      while(i<len(notes)):
         print(f'№ {i+1} {notes[i].get("Название:")}')
         i+=1

print("\nВыберите необходимое действие:\n"
          "1. Отобразить весь список заметок\n"
          "2. Сохранить заметки\n"
          "3. Создать заметку\n"
          "4. Прочитать заметку\n"
          "5. Редактировать заметку\n"
          "6. Удалить заметку\n"
          "0. Закончить работу с сохранением всех новых заметок\n"
      "!!!ВАЖНО!!! Cначала необходимо загрузить список заметок командой '8' ")

while True:
   comand = input("Введите комманду: ")
   if comand == "1":
      show_all_notes()
   elif comand == "8":
      load()
   elif comand == "2":
      save()
   elif comand == "3":
      add()
   elif comand == "4":
      open_note()
   elif comand == "5":
      red()
   elif comand == "6":
      delite()   
   elif comand == "0":
      save()
      print("Телефонный справочник сохранен. Всего хорошего!")
      break
