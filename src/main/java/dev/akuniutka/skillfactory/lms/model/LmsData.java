package dev.akuniutka.skillfactory.lms.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class LmsData {
    @XmlElementWrapper(name = "studentsInfo")
    @XmlElement(name = "studentEntry")
    private List<Student> students;
    @XmlElementWrapper(name = "universitiesInfo")
    @XmlElement(name = "universityEntry")
    private List<University> universities;
    @XmlElementWrapper(name = "statisticalInfo")
    @XmlElement(name = "statisticsEntry")
    private List<Statistics> statistics;
    private Date processedAt;

    public LmsData() {
        processedAt = new Date();
    }
}
