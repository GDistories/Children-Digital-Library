package edu.cn.bookadminister.app;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.cn.bookadminister.R;
import edu.cn.bookadminister.activity.BookDetailActivity;
import edu.cn.bookadminister.bean.BookBean;


public class BookListFragment extends Fragment {

    private RecyclerView recyclerView;
    BookAdapter bookAdapter;

    public BookListFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<BookBean> bookBeans = new ArrayList<>();
        englishData(bookBeans);
        recyclerView = getActivity().findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        bookAdapter = new BookAdapter(R.layout.book_item,bookBeans);
        recyclerView.setAdapter(bookAdapter);
        bookAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                BookBean item = (BookBean) adapter.getItem(position);
                Intent intent = new Intent("com.book.detail");
                Bundle bundle = new Bundle();
                bundle.putSerializable("item",item);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public class BookAdapter extends BaseQuickAdapter<BookBean, BaseViewHolder>{
        public BookAdapter(int layoutResId, List<BookBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder holder, BookBean bookBean_) {
            holder.setText(R.id.book_name,bookBean_.getTitle())
                    .setText(R.id.book_content,bookBean_.getJianjie())
                    .setImageResource(R.id.image,bookBean_.getIcon());
        }
    }

    private void chineseData(List<BookBean> bookBeans) {
        bookBeans.add(new BookBean("平凡的世界",
                "《平凡的世界》是中国作家路遥创作的一部全景式地表现中国当代城乡社会生活的百万字长篇小说。",
                "该书以中国70年代中期到80年代中期十年间为背景，通过复杂的矛盾纠葛，以孙少安和孙少平两兄弟为中心，刻画了当时社会各阶层众多普通人的形象；劳动与爱情、挫折与追求、痛苦与欢乐、日常生活与巨大社会冲突纷繁地交织在一起，深刻地展示了普通人在大时代历史进程中所走过的艰难曲折的道路。",
                R.mipmap.pingfan,
                true,false));
        bookBeans.add(new BookBean("活着",
                "《活着》是中国当代作家余华创作的长篇小说，首次发表于《收获》1992年第6期。",
                "《活着》讲述了在大时代背景下，随着内战、三反五反、大跃进、“文化大革命”等社会变革，徐福贵的人生和家庭不断经受着苦难，到了最后所有亲人都先后离他而去，仅剩下年老的他和一头老牛相依为命。小说以普通、平实的故事情节讲述了在急剧变革的时代中福贵的不幸遭遇和坎坷命运，在冷静的笔触中展现了生命的意义和存在的价值，揭示了命运的无奈，与生活的不可捉摸。",
                R.mipmap.huozhe,
                false,true));
        bookBeans.add(new BookBean("人间失格",
                "《人间失格》（又名《丧失为人的资格》）日本小说家太宰治创作的中篇小说，发表于1948年，是一部半自传体的小说。",
                "《人间失格》以“我”看到叶藏的三张照片后的感想开头，中间是叶藏的三篇手记，而三篇手记与照片对应，分别介绍了叶藏幼年、青年和壮年时代的经历，描述了叶藏是如何一步一步走向丧失为人资格的道路的。",
                R.mipmap.renjian,
                false,false));
        bookBeans.add(new BookBean("月亮与六便士",
                "《月亮与六便士》是英国著名作家毛姆的代表作之一，作品以法国印象派画家保罗高更的生平为素材，深刻提示出个性、天才与现实社会的矛盾。",
                "《月亮与六便士》有着广阔的生命视野，对人性的提示令人颤栗，在看似幽默随意的叙述中光涌着一股惊心动魄的力量，并有着一种残忍的意味。英国著名作家弗吉尼亚·伍尔芙评论说：“读《月亮与六便士》就像一头撞在了高耸的冰山上，令平庸的日常生活彻底解体！",
                R.mipmap.yueliang,
                false,false));
        bookBeans.add(new BookBean("云边有个小卖部",
                "《云边有个小卖部》是中国当代作家张嘉佳创作的长篇小说。",
                "该小说讲述小镇青年刘十三回到表面平静却暗潮涌动的小镇，与外婆和几位发小所发生的一连串悲欢离合的故事。该小说表达了对故乡、对亲情的衷心倾诉。外婆王莺莺的乐观坚韧和无限包容，程霜像一道光一样短暂又永恒，刘十三的奋斗与卑微，云边镇迷人的自然风景、悠然的生活节奏，共同构成了云边故乡的美好意境。",
                R.mipmap.yunbian,
                false,false));
    }
    private void englishData(List<BookBean> bookBeans) {
        bookBeans.add(new BookBean("Zhongli: The Listener",
                "Item ID: 001\nType: Video\nBarcode: 10000001" ,
                "Item ID: 001\nType: Video\nBarcode: 10000001\nDescription: After the end of \"The Annals of Liyue\", a member of the audience stands before a painted screen of mountains and rivers, as if expecting to hear echoes from the past.\n" +
                        "But the painting on the screen remains silent, for that was a tale from three thousand years ago.",
                R.mipmap.zhongli,
                true,false));
        bookBeans.add(new BookBean("Let the wind tell you",
                "Item ID: 002\nType: Audio\nBarcode: 20000001",
                "Item ID: 002\nType: Audio\nBarcode: 20000001\nDescription: Performed by\n" +
                        "Klee - Flower Ling\n" +
                        "Wendy - Meow☆Sauce\n" +
                        "Qiqi--Yan Ning cccccc\n" +
                        "Xiao - kinsen\n" +
                        "\n" +
                        "Painter: - Black Deer Plain -, Kurattes\n" +
                        "\n" +
                        "Lyrics/Producer: ChiliChill\n" +
                        "Arranger: ChiliChill\n" +
                        "Bass: Ziming Feng, Shinya Yamaguchi\n" +
                        "Flute: Salit Lahav\n" +
                        "Strings written by Jingcheng Hu\n" +
                        "Violin: Pang Gao / Zhang Hao\n" +
                        "Viola: Bi Fang\n" +
                        "Lang Ying, Cello\n" +
                        "String Recording: Xinda Li@JiuZiTianCheng\n" +
                        "Vocal Recording Studio: YIHE Studio\n" +
                        "Mixing / Mastering: ChiliChill\n" +
                        "\n" +
                        "ChiliChill is an indie duo consisting of members Yu H. and CuSummer.\n",
                R.mipmap.letwindtellyou,
                false,true));
        bookBeans.add(new BookBean("The world lost",
                "Item ID: 003\nType: Book\nBarcode: 30000001",
                "Item ID: 003\nType: Book\nBarcode: 30000001\nDescription: \"Lost in The World\" begins with \"I\" after seeing three photos of Ye Zang. In the middle are three notes of Ye Zang. The three notes correspond to the photos and respectively introduce the experiences of Ye Zang in his childhood, youth and prime years, and describe how Ye Zang steps towards the road of losing his human qualifications.",
                R.mipmap.renjian,
                false,false));
        bookBeans.add(new BookBean("Moon and sixpence",
                "Item ID: 004\nType: Book\nBarcode: 30000002",
                "Item ID: 004\nType: Book\nBarcode: 30000002\nDescription: \"The Moon and Sixpence\" has a broad vision of life, a chilling reminder of humanity, a thrilling power of light and a sense of cruelty in a seemingly humorous and casual narrative. Virginia Woolf, a famous British writer, commented, \"Reading The Moon and Sixpence is like hitting a towering iceberg, breaking up the banal life of everyday life!",
                R.mipmap.yueliang,
                false,false));
        bookBeans.add(new BookBean("A kiosk by the clouds",
                "Item ID: 005\nType: Book\nBarcode: 30000003",
                "Item ID: 005\nType: Book\nBarcode: 30000003\nDescription: The novel tells the story of liu Shisan, a young man in a small town, who returns to a quiet but turbulent town. This novel expresses the heartfelt talk to the hometown and the family. Grandmother Wang Yingying's optimism tenacity and infinite tolerance, Cheng Frost like a light as short and eternal, Liu Shisan's struggle and humble, yunbian town charming natural scenery, leisurely pace of life, together constitute a beautiful mood of yunbian hometown.",
                R.mipmap.yunbian,
                false,false));
    }
}
