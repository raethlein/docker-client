/*
 * Copyright (c) 2014 Spotify AB.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.spotify.docker.client.messages;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE)
public class Network {

  @JsonProperty("Name") private String name;
  @JsonProperty("Id") private String id;
  @JsonProperty("Scope") private String scope;
  @JsonProperty("Driver") private String driver;
  @JsonProperty("IPAM") private Ipam ipam;
  @JsonProperty("Options") private Map<String, String> options;

  private Network() {
  }

  private Network(final Builder builder) {
    this.name = builder.name;
    this.options = builder.options;
    this.driver = builder.driver;
  }

  public String name() {
    return name;
  }

  public String id() {
    return id;
  }

  public String scope() {
    return scope;
  }

  public String driver() {
    return driver;
  }
  public Map<String, String> options() {
    return options;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Network that = (Network) o;

    return Objects.equal(this.name, that.name) &&
        Objects.equal(this.id, that.id) &&
        Objects.equal(this.scope, that.scope) &&
        Objects.equal(this.driver, that.driver) &&
        Objects.equal(this.options, that.options);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(name, id, scope, driver, options);
  }

  public Builder toBuilder() {
    return new Builder(this);
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("name", name)
        .add("id", id)
        .add("scope", scope)
        .add("driver", driver)
        .add("options", options)
        .toString();
  }

  public static class Builder {

    private String name;
    private Map<String, String> options = new HashMap<String, String>();
    private String driver;

    private Builder() {
    }

    private Builder(Network network) {
      this.name = network.name;
      this.options = network.options;
      this.driver = network.driver;
    }

    public Builder name(final String name) {
      if (name != null && !name.isEmpty()) {
        this.name = name;
      }
      return this;
    }

    public Builder option(final String key, final String value) {
      if (key != null && !key.isEmpty()) {
        this.options.put(key, value);
      }
      return this;
    }

    public Builder driver(final String driver) {
      if (driver != null && !driver.isEmpty()) {
        this.driver = driver;
      }
      return this;
    }

    public Network build() {
      return new Network(this);
    }

  }

}
