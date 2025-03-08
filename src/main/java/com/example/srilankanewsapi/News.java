package com.example.srilankanewsapi;

public class News {

    private String topic;
    private String description;
    private String link;

    public News(String topic, String description, String link) {
        this.topic = topic;
        this.description = description;
        this.link = link;
    }

    public News() {
    }

    public String getTopic() {
        return this.topic;
    }

    public String getDescription() {
        return this.description;
    }

    public String getLink() {
        return this.link;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof News)) return false;
        final News other = (News) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$topic = this.getTopic();
        final Object other$topic = other.getTopic();
        if (this$topic == null ? other$topic != null : !this$topic.equals(other$topic)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$link = this.getLink();
        final Object other$link = other.getLink();
        if (this$link == null ? other$link != null : !this$link.equals(other$link)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof News;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $topic = this.getTopic();
        result = result * PRIME + ($topic == null ? 43 : $topic.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $link = this.getLink();
        result = result * PRIME + ($link == null ? 43 : $link.hashCode());
        return result;
    }

    public String toString() {
        return "News(topic=" + this.getTopic() + ", description=" + this.getDescription() + ", link=" + this.getLink() + ")";
    }
}