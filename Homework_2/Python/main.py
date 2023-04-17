# This is a sample Python script.

# Press ⌃R to execute it or replace it with your code.
# Press Double ⇧ to search everywhere for classes, files, tool windows, actions, and settings.

def get_yn_stadium_lights() -> bool:
    ans = input("Има ли осветление[y/n]? ")
    if ans.lower() == 'y':
        return True
    elif ans.lower() == 'n':
        return False
    else:
        print("Грешен отговор! Моля отговорете с 'y' или 'n'.")
        return get_yn_stadium_lights()


def Task1():
    """
     За даден стадион имаме следните характеристики:
    ▪ Наименование на стадиона
    ▪ Капацитет в бр. седящи места (в променливата capacity) ▪ Дали има осветление (в променливата hasLights)
    ▪ Площ в квадратни метри (дробно число)
    Напишете програма, която позволява на потребителя да въведе характеристики за стадион и
     след това ги отпечатва в подходящ вид. Стремете се да имате добро потребителско
     изживяване (да е ясно какво се очаква от потребителя).

    :param name:
    :return:
    """
    stadium_name = input("Въведете името на стадиона: ")
    capacity = input("Какъв е капацитетът му? ")
    area_sqm = input("Каква е площа на стадион " + stadium_name + " в квадратни метри?")
    has_lights = get_yn_stadium_lights()

    print(f"Стадион: {stadium_name}")
    print(f"Капацитет: {capacity}")
    print(f"Площ: {area_sqm}")
    if has_lights:
        print("Осветление: Да")
    else:
        print("Осветление: Не")
def Task2():
    """
    Да се напише програма, която позволява на потребителя да въведе цяло число N и
    след това отпечатва цяла част и остатък при деление на N с 3.
    """
    N = int(input("Въведете число: "))
    result_part_1 = int(N / 3)
    result_part_2 = N % 3
    print(f"При делене на 3 има {result_part_1} цели части и остатък от {result_part_2}.")

def Task3():
    """
     За даден автомобил са дадени следните характеристики:
     ▪ Възраст в години (age)
     ▪ Цена в хил.лв. (price)
    Един автомобил е от висок клас, ако е по-стар от 5 години и струва над 20 хил.лв. или е
     на 5 или по-малко години и струва над 40 хил.лв.
    Прочетете двете стойности от конзолата.
    Напишете израз, който определя дали автомобил с дадени характеристики е от висок клас.
    Принтирайте резултатът от израза (true или false).
    """
    age = int(input("На колко години е автомобилът? "))
    price = int(input("Колко пари струва (хил. лв)? "))

    high_class = (age > 5 and price > 20000) or (age <=5 and price > 40000)
    if high_class:
        print("Автомобилът е висок клас.")
    else:
        print("Автомобилът е нисък клас.")



if __name__ == '__main__':
    # Task1()
    # Task2()
    Task3()