    package mongodb.demo.mongodemo.models;

    import org.springframework.data.annotation.Id;
    import org.springframework.data.mongodb.core.index.CompoundIndex;
    import org.springframework.data.mongodb.core.index.IndexDirection;
    import org.springframework.data.mongodb.core.index.Indexed;
    import org.springframework.data.mongodb.core.mapping.Document;
    import org.springframework.data.mongodb.core.mapping.Field;

    @Document(collection = "buyer")
    @CompoundIndex(def = "{'email': 1, 'age': -1}", unique = true, background = false, sparse = false)
    public class Buyer {
        @Id
        private String id;
        @Field(name = "name")
        @Indexed(unique = true, sparse = true, direction = IndexDirection.ASCENDING, background = false)
        private String name;
        private Integer age;
        private String email;

        private String male;

        public Buyer(String name, Integer age, String male, String email) {
            this.name = name;
            this.age = age;
            this.male = male;
            this.email = email;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getMale() {
            return male;
        }

        public void setMale(String male) {
            this.male = male;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return "Buyer{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    ", male='" + male + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }
