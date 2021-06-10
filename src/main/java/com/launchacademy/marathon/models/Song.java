package com.launchacademy.marathon.models;

import com.fasterxml.jackson.datatype.jsr310.deser.YearDeserializer;
import java.time.Year;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.stream.XMLInputFactory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "songs")
@NoArgsConstructor
@Getter
@Setter
public class Song {
  @Id
  @SequenceGenerator(name="song_generator", sequenceName = "songs_id_seq", allocationSize = 1)
  @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "song_generator")
  @Column(name="id", nullable = false, unique = true)
  private Integer id;

  @NotBlank
  @Size(min = 1, max = 25)
  @NotNull
  @Column(name="title", nullable = false)
  private String title;

  @NotBlank
  @Size(min = 1, max = 15)
  @Pattern(regexp = "^[a-zA-Z\\s]*$")
  @Column(name="genre")
  private String genre;

  @Max(2021)
  @NotNull
  @Min(1000)
  @Column(name="release_year", nullable = false)
  private Integer releaseYear;

  @NotNull
  @Column(name="explicit_content", nullable = false)
  private Boolean explicitContent;
}
