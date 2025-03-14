package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.entity.Attendance;
import org.example.entity.Employee;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Employee employee = new Employee();
            employee.setFirstName("Ivan");
            employee.setLastName("Petrov");
            employee.setEmail("ivan@example.com");
            employee.setPhone("123456789");
            employee.setHireDate(java.time.LocalDate.now());

            entityManager.persist(employee);
            entityManager.flush(); // Генерация ID

            System.out.println("✅ Создан новый сотрудник: " + employee.getFirstName() + " " + employee.getLastName());

            // ✅ Создаем запись о посещаемости
            Attendance attendance = new Attendance();
            attendance.setEmployee(employee);
            attendance.setDate(java.time.LocalDate.now());
            attendance.setPresent(true);

            entityManager.persist(attendance);

            transaction.commit(); // Закрываем транзакцию один раз

            System.out.println("✅ Добавлена посещаемость для: " + employee.getFirstName() + " " + employee.getLastName());

            // 🟢 Получаем всех сотрудников и выводим
            System.out.println("Список сотрудников:");
            entityManager.createQuery("SELECT e FROM Employee e", Employee.class)
                    .getResultList()
                    .forEach(emp -> System.out.println(emp.getFirstName() + " " + emp.getLastName() + " - " + emp.getEmail()));

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            entityManager.close();
            HibernateUtil.shutdown();
        }
    }
}


//    Регистрация нового сотрудника (Добавление записи в таблицу Employee)
//    Учет рабочего времени (Добавление записей в Attendance)
//    Запрос на отпуск (Создание LeaveRequest)
//    Расчет зарплаты (Создание Payroll и расчет на основе WorkTime)
//    Оценка производительности (Добавление PerformanceReview)
//    Изменение должности сотрудника (Обновление данных в Position)
//    Вывод статистики по посещаемости и зарплате

