package com.kdbf.digitalLibrary.adapters.out.persistence.entity;

import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "book")
@Setter
@Getter
public class BookJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, unique = true, updatable = false)
  private UUID publicId = UUID.randomUUID();
  @Column
  private String title;
  @Column
  private String language;
  @Column
  private int downloads;

  @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
  @JoinColumn(name = "author_id", nullable = false)
  private AuthorJpaEntity author;

  public BookJpaEntity(Long id, String title, String language, int downloads, AuthorJpaEntity author) {
    this.id = id;
    this.title = title;
    this.language = language;
    this.downloads = downloads;
    this.author = author;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + ((publicId == null) ? 0 : publicId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    BookJpaEntity other = (BookJpaEntity) obj;
    if (id != other.id)
      return false;
    if (publicId == null) {
      if (other.publicId != null)
        return false;
    } else if (!publicId.equals(other.publicId))
      return false;
    return true;
  }

}
