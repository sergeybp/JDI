package com.epam.jdi.uitests.testing.simple.examples;

import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.core.interfaces.complex.interfaces.ICell;
import com.epam.jdi.uitests.testing.TestsBase;
import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

import static com.epam.jdi.uitests.core.interfaces.complex.interfaces.Column.inColumn;
import static com.epam.jdi.uitests.core.interfaces.complex.interfaces.WithValue.withValue;
import static com.epam.jdi.uitests.testing.career.page_objects.site.epam.EpamSite.*;


public class W3CEntityTableExamples extends TestsBase {
    @BeforeMethod
    public void before(Method method) {
        jobListingPage.isOpened();
        Assert.isFalse(jobListingPage.jobsList::isEmpty);
    }
    @Test
    public void getTableInfoExample() {
     /*   jobListingPage.isOpened();
        List<JobRecord> rows = jobList.getRows();
        Job job = jobList.entity(jobName, column(name.toString()));
        List<Job> jobs = jobList.entities();*/
    }

    @Test
    public void searchInTableExample() {
        jobListingPage.isOpened();
        Assert.isFalse(jobListingPage.jobsList::isEmpty);
        jobListingPage.jobsList
                .row(withValue("Senior QA Automation Engineer"), inColumn("name"))
                .get("apply").select();

        jobDescriptionPage.checkOpened();
    }

    @Test
    public void searchContainsInTableExample() {
        jobListingPage.isOpened();
        Assert.isFalse(jobListingPage.jobsList::isEmpty);
        jobListingPage.jobsList
                .rowContains("Automation Engineer", inColumn("name"))
                .get("apply").select();

        jobDescriptionPage.checkOpened();
    }

    @Test
    public void searchMatchInTableExample() {
        jobListingPage.isOpened();
        Assert.isFalse(jobListingPage.jobsList::isEmpty);
        jobListingPage.jobsList
                .rowMatch(".+ Automation Engineer", inColumn("name"))
                .get("apply").select();

        jobDescriptionPage.checkOpened();
    }

    @Test
    public void searchContainsListInTableExample() {
        jobListingPage.isOpened();
        Assert.isFalse(jobListingPage.jobsList::isEmpty);
        MapArray<String, ICell> firstRow = jobListingPage.jobsList.rows(
                "name~=Automation Engineer",
                "category*=.*Test Engineering")
                .first().value;

        Assert.areEquals(firstRow.get("name").getText(), "Senior QA Automation Engineer");
        Assert.areEquals(firstRow.get("category").getText(), "Software Test Engineering");
    }

    @Test
    public void searchByMultiCriteriaInTableExample() {
        jobListingPage.isOpened();
        Assert.isFalse(jobListingPage.jobsList::isEmpty);
        MapArray<String, ICell> firstRow = jobListingPage.jobsList.rows(
                "name=Senior QA Automation Engineer",
                "category=Software Test Engineering")
                .first().value;

        Assert.areEquals(firstRow.get("name").getText(), "Senior QA Automation Engineer");
        Assert.areEquals(firstRow.get("category").getText(), "Software Test Engineering");
    }
}
