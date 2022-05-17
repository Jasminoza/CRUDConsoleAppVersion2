## **Задача**: 
##### Необходимо реализовать консольное CRUD приложение, которое имеет следующие сущности:

* ###### Developer (id, firstName, lastName, List<Skill> skills, Specialty specialty)
* ###### Skill
* ###### Specialty
* ###### Status (enum ACTIVE, DELETED)

##### В качестве хранилища данных необходимо использовать json файлы:

1. ###### developers.json
2. ###### skills.json
3. ###### specialties.json

Пользователь в консоли должен иметь возможность создания, получения, редактирования и удаления данных.

##### Слои:

* ###### model - POJO классы
* ###### repository - классы, реализующие доступ к текстовым файлам
* ###### controller - обработка запросов от пользователя
* ###### view - все данные, необходимые для работы с консолью

Например: Developer, DeveloperRepository, DeveloperController, DeveloperView и т.д.

Для репозиторного слоя желательно использовать базовый интерфейс:
interface GenericRepository<T,ID>

interface DeveloperRepository extends GenericRepository<Developer, Long>

class GsonDeveloperRepositoryImpl implements DeveloperRepository

