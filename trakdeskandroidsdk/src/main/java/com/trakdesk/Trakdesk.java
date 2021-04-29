package com.trakdesk;

import android.util.Base64;
import com.trakdesk.Exception.InvalidConfigurationException;
import com.trakdesk.Resources.Agents;
import com.trakdesk.Resources.Announcements;
import com.trakdesk.Resources.Brands;
import com.trakdesk.Resources.BusinessHours;
import com.trakdesk.Resources.Companies;
import com.trakdesk.Resources.CompanyFields;
import com.trakdesk.Resources.Contacts;
import com.trakdesk.Resources.ContactFields;
import com.trakdesk.Resources.Conversations;
import com.trakdesk.Resources.Departments;
import com.trakdesk.Resources.EmailAccounts;
import com.trakdesk.Resources.Groups;
import com.trakdesk.Resources.Helpdesk;
import com.trakdesk.Resources.Knowledgebase;
import com.trakdesk.Resources.Roles;
import com.trakdesk.Resources.ScheduledTickets;
import com.trakdesk.Resources.SlaPolicies;
import com.trakdesk.Resources.Tickets;
import com.trakdesk.Resources.TicketFields;

import com.trakdesk.Resources.TimeEntries;

public class Trakdesk {

    /**
     * This contains the Trakdesk API version that is accessed by this SDK
     */
    private String version = "v1";

    /**
     * This contains the Trakdesk API base URL
     */
    public static String baseUrl;

    /**
     * This contains the auth API key/username:password
     */
    public String apiKey;

    /**
     * This contains the helpdesk domain
     */
    public String domain;

    /**
     * This contains the auth API key/username:password
     */
    public static String auth;

    /**
     * Agents resource
     */
    public Agents agents;

    /**
     * Announcements resource
     */
    public Announcements announcements;

    /**
     * Brands resource
     */
    public Brands brands;

    /**
     * Business hours resource
     */
    public BusinessHours businessHours;

    /**
     * Companies resource
     */
    public Companies companies;

    /**
     * CompanyFields resource
     */
    public CompanyFields companyFields;

    /**
     * Contacts resource
     */
    public Contacts contacts;

    /**
     * ContactFields resource
     */
    public ContactFields contactFields;

    /**
     * Conversations resource
     */
    public Conversations conversations;

    /**
     * Departments resource
     */
    public Departments departments;

    /**
     * EmailAccounts resource
     */
    public EmailAccounts emailAccounts;

    /**
     * Groups resource
     */
    public Groups groups;

    /**
     * Helpdesk resource
     */
    public Helpdesk helpdesk;

    /**
     * Knowledgebase resource
     */
    public Knowledgebase knowledgebase;

    /**
     * Roles resource
     */
    public Roles roles;

    /**
     * ScheduledTickets resource
     */
    public ScheduledTickets scheduledTickets;

    /**
     * SlaPolicies resource
     */
    public SlaPolicies slaPolicies;

    /**
     * Tickets resource
     */
    public Tickets tickets;

    /**
     * TimeEntries resource
     */
    public TimeEntries timeEntries;

    /**
     * TicketFields resource
     */
    public TicketFields ticketFields;

    /**
     * Constructor a new api instance
     *
     * @param apiKey {@link String}
     * @param domain {@link String}
     * @throws Exception InvalidConfigurationException
     */
    public Trakdesk(String apiKey, String domain) throws InvalidConfigurationException {

        this.apiKey = apiKey;
        this.domain = domain;

        validateRequestArguments();
        baseUrl = "https://" + this.domain + ".trakdesk.com/api/" + version;
        auth = "Basic " + Base64.encodeToString(this.apiKey.getBytes(), Base64.DEFAULT);

        setResources();
    }

    /**
     * Validate request arguments
     * This validates API key/helpdesk sub-domain
     *
     * @throws Exception InvalidConfigurationException
     */
    private void validateRequestArguments() throws InvalidConfigurationException {
        if (this.apiKey.isEmpty()) {
            throw new InvalidConfigurationException("Missing API key");
        }
        if (this.domain.isEmpty()) {
            throw new InvalidConfigurationException("Missing sub-domain");
        }
    }

    private void setResources() {
        agents = new Agents();
        announcements = new Announcements();
        brands = new Brands();
        businessHours = new BusinessHours();
        companies = new Companies();
        companyFields = new CompanyFields();
        contacts = new Contacts();
        contactFields = new ContactFields();
        conversations = new Conversations();
        departments = new Departments();
        emailAccounts = new EmailAccounts();
        groups = new Groups();
        helpdesk = new Helpdesk();
        knowledgebase = new Knowledgebase();
        roles = new Roles();
        scheduledTickets = new ScheduledTickets();
        slaPolicies = new SlaPolicies();
        tickets = new Tickets();
        timeEntries = new TimeEntries();
        ticketFields = new TicketFields();
    }
}